/**
 * 
 */
package com.stationmillenium.rdsmanager.services;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.stationmillenium.rdsmanager.beans.mainconfig.MainConfigProperties;
import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.schema.currentsong.CurrentSong;
import com.stationmillenium.rdsmanager.services.cleaner.CleanerService;
import com.stationmillenium.rdsmanager.services.currenttitle.CurrentTitleGrabberService;
import com.stationmillenium.rdsmanager.services.rs232.RDSDisplayManagerService;


/**
 * Service managing the general display (cron job)
 * @author vincent
 *
 */
@Service
public class MainService {
	
	//logger
	private final static Logger LOGGER = LoggerFactory.getLogger(MainService.class);

	//services
	//title grabber
	@Autowired
	private CurrentTitleGrabberService currentTitleGrabberService;
		
	//title cleaner
	@Autowired
	private CleanerService cleanerService; 
	
	//display on rds service
	@Autowired
	private RDSDisplayManagerService rdsDisplayManagerService;
	
	//config
	@Autowired
	private MainConfigProperties mainConfigProperties;
	
	//instances
	//boolean if service is enabled
	private Boolean serviceEnabled = true;
	
	//are we in idle time ?
	private boolean idleTime = true;
	
	//date of the last action
	private Calendar lastActionDate;
	
	//last broadcasted title
	BroadcastableTitle broadcastableTitle;
	
	/**
	 * Set the service enabled
	 * @param serviceEnabled
	 */
	public void setServiceEnabled(boolean serviceEnabled) {
		synchronized (this.serviceEnabled) {
			this.serviceEnabled = serviceEnabled;
		}
	}

	/**
	 * Is the service enabled ? 
	 * @return the serviceEnabled
	 */
	public boolean isServiceEnabled() {
		boolean enableLocal = false;
		synchronized (serviceEnabled) { //get enable state with local copy
			enableLocal = serviceEnabled;
		}
		return enableLocal;
	}

	/**
	 * Display on RDS - method invoked periodically (every 5 s)
	 */
	@Scheduled(fixedDelay = 5000)
	public void displayOnRDS() {
		try  {
			if (isServiceEnabled()) { //if service is enabled
				LOGGER.debug("Service is enabled");
				if (isPauseTimeElapsed()) { //pause time elapsed - changing display and mode
					LOGGER.debug("Pause elasped");
					
					if (idleTime) { //we switch the mode
						LOGGER.debug("Switching to title mode");
						broadcastableTitle = getCurrentBroadcastableTitle();
						if (broadcastableTitle != null) { //if there is a title to display
							LOGGER.debug("Title to display : " + broadcastableTitle);
							rdsDisplayManagerService.displayTitle(broadcastableTitle);
							
						} else { //no title to display
							LOGGER.debug("Broadcastable title is null - display idle text");
							rdsDisplayManagerService.displayIdleText();
						}
						
						idleTime = false; 
						
					} else { //idle mode
						LOGGER.debug("Switching to idle mode");
						rdsDisplayManagerService.displayIdleText();
						idleTime = true;
					}
					
					//save last action date
					lastActionDate = Calendar.getInstance();
					LOGGER.debug("Display process ended");
					
				} else if (!idleTime) { //we are not in idle time - check if title needs update 
					LOGGER.debug("Check if current displayed title needs update");
					BroadcastableTitle titleFromServer = getCurrentBroadcastableTitle();
					if ((titleFromServer != null) && (broadcastableTitle != null) && (!broadcastableTitle.equals(titleFromServer))) { //if titles are not null and not the same
						LOGGER.debug("Titles are different : update display - Old title : " + broadcastableTitle + " - New title : " + titleFromServer);
						broadcastableTitle = titleFromServer;
						rdsDisplayManagerService.displayTitle(broadcastableTitle);
					} 
					
				} else 
					LOGGER.debug("Pause not elasped - wait | idle mode : " + idleTime);
				
			} else if (!idleTime) { //if service is disabled and not in idle time 
				LOGGER.debug("Service disabled but not in idle - set in idle");
				rdsDisplayManagerService.displayIdleText();
				idleTime = true;
			} else 
				LOGGER.debug("Service disabled");
		} catch (Exception e) {
			LOGGER.error("Error global", e);
		}
	}
	
	/**
	 * Is the pause time elapsed ?
	 * @return <code>true</code> if elsaped, <code>false</code> if not
	 */
	private boolean isPauseTimeElapsed() {
		if (lastActionDate != null) {
			Calendar pauseTimeCalendar = Calendar.getInstance();
			pauseTimeCalendar.add(Calendar.SECOND, -((idleTime) ? 
					mainConfigProperties.getIdleModePauseTime() : mainConfigProperties.getTitleModePauseTime()));
			boolean pauseElapsed = pauseTimeCalendar.after(lastActionDate);
			return pauseElapsed;
			
		} else {
			LOGGER.debug("Last action date is null - pause elapsed");
			return true;
		}
	}
	
	/**
	 * Get the title to broadcast 
	 * @return the {@link BroadcastableTitle}
	 */
	private BroadcastableTitle getCurrentBroadcastableTitle() {
		CurrentSong song = currentTitleGrabberService.getCurrentTitle();
		BroadcastableTitle broadcastableTitle = cleanerService.cleanTitle(song);
		LOGGER.debug("Grabber broadcastable title : " + broadcastableTitle);
		return broadcastableTitle;
	}
	
}
