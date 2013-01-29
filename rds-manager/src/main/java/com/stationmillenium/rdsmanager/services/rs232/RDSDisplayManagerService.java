package com.stationmillenium.rdsmanager.services.rs232;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationmillenium.rdsmanager.beans.rs232.RDSDisplayManagerProperties;
import com.stationmillenium.rdsmanager.dto.documents.RDSDisplay;
import com.stationmillenium.rdsmanager.dto.documents.subs.RS232Commands;
import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.repositories.MongoDBRepository;

/**
 * Service to manager text displayed on RDS
 * @author vincent
 *
 */
@Service
public class RDSDisplayManagerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RDSDisplayManagerService.class);
	
	//configuration
	@Autowired
	private RDSDisplayManagerProperties rdsDisplayManagerProperties;
	
	//db repository to log commands
	@Autowired
	private MongoDBRepository mongoDBRepository;
	
	/**
	 * Display a title on PS and RadioText
	 * @param titleToDisplay the title to display
	 */
	public void displayTitle(BroadcastableTitle titleToDisplay) {
		LOGGER.debug("Display title on RDS : " + titleToDisplay);
		String psCommandToSend = preparePSTitleCommand(titleToDisplay);		
		String rtCommandToSend = prepareRTTitleCommand(titleToDisplay);
		
		//send ps command
		sendCommandsToRDS(psCommandToSend, rtCommandToSend, titleToDisplay);
	}

	/**
	 * Prepapre the RT title command to display title on RDS
	 * @param titleToDisplay the title
	 * @return the command as string
	 */
	private String prepareRTTitleCommand(BroadcastableTitle titleToDisplay) {
		String artist = titleToDisplay.getArtist();
		artist = artist.charAt(0) + artist.substring(1).toLowerCase(); //lowercase but 1st letter uppercase
		String title = titleToDisplay.getTitle();
		title =  title.charAt(0) +  title.substring(1).toLowerCase(); //lowercase but 1st letter uppercase
		String rtText = artist + " " + rdsDisplayManagerProperties.getRtSeparator() + " " + title;
		if (rtText.length() > rdsDisplayManagerProperties.getMaxLength())
			rtText = rtText.substring(0, rdsDisplayManagerProperties.getMaxLength() - 1);
		String rtCommandToSend = rdsDisplayManagerProperties.getRtCommandPrefix() + rtText;
		return rtCommandToSend;
	}

	/**
	 * Prepare the PS command to display title on RDS
	 * @param titleToDisplay the title 
	 * @return the command as string
	 */
	private String preparePSTitleCommand(BroadcastableTitle titleToDisplay) {
		String psText = titleToDisplay.getArtist() + " " + titleToDisplay.getTitle();
		if (psText.length() > rdsDisplayManagerProperties.getMaxLength())
			psText = psText.substring(0, rdsDisplayManagerProperties.getMaxLength() - 1);
		String psCommandToSend = rdsDisplayManagerProperties.getPsCommandPrefix() + psText;
		return psCommandToSend;
	}
	
	/**
	 * Display the idle text
	 */
	public void displayIdleText() {
		LOGGER.debug("Display the idle text");
		String psCommandToSend = rdsDisplayManagerProperties.getPsCommandPrefix() + rdsDisplayManagerProperties.getPsIdle();
		String rtCommandToSend = rdsDisplayManagerProperties.getRtCommandPrefix() + rdsDisplayManagerProperties.getRtIdle();
		
		//send command
		sendCommandsToRDS(psCommandToSend, rtCommandToSend, null);
	}
	
	/**
	 * Send command to the RDS
	 * @param psCommand the PS command as string
	 * @param rtCommand the RT command as String
	 * @param title title to log
	 */
	private void sendCommandsToRDS(String psCommand, String rtCommand, BroadcastableTitle title) {
		LOGGER.debug("Command to send for PS : " + psCommand);
		LOGGER.debug("Command to send for RT : " + rtCommand);
		
		//send command
		
		//db log
		logCommandsIntoDB(psCommand, rtCommand, title);
	}

	/**
	 * Log the commands into db
	 * @param psCommand the PS command sent
	 * @param rtCommand the RT command sent
	 * @param title title to display
	 */
	private void logCommandsIntoDB(String psCommand, String rtCommand, BroadcastableTitle title) {
		//log into db
		RDSDisplay rdsDisplayDocument = new RDSDisplay();
		rdsDisplayDocument.setBroadcastableTitle(title);
		rdsDisplayDocument.setDate(Calendar.getInstance().getTime());
		rdsDisplayDocument.setRs232Commands(new RS232Commands());
		rdsDisplayDocument.getRs232Commands().setPsCommand(psCommand);
		rdsDisplayDocument.getRs232Commands().setPsCommandReturn("+");
		rdsDisplayDocument.getRs232Commands().setRtCommand(rtCommand);
		rdsDisplayDocument.getRs232Commands().setRtCommandReturn("+");
		
		mongoDBRepository.insertRDSDisplay(rdsDisplayDocument);
	}
	
}
