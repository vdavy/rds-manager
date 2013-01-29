/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.services.cleaner;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.schema.currentsong.CurrentSong;
import com.stationmillenium.rdsmanager.services.cleaner.CleanerService;
import com.stationmillenium.rdsmanager.services.currenttitle.CurrentTitleGrabberService;

/**
 * Test the {@link CleanerService}
 * @author vincent
 *
 */
public class TestCleanerService {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCleanerService.class);
	
	//current title grabber service
	@Autowired
	private CurrentTitleGrabberService currentTitleGrabberService;
	
	//service to test
	@Autowired
	private CleanerService cleanerService;
	
	//instances
	private CurrentSong currentSong;
	
	@Before
	public void init() {
		currentSong = new CurrentSong();
		currentSong.setArtist("Alïcià Kéys Feat0. Nicki Mînaj");
		currentSong.setTitle("Girl On Fire [Infèrnô1 Version]");
	}
	
	/**
	 * Test the cleaner service
	 */
	@Test
	public void testCleanerService() {
		//process
		BroadcastableTitle broadcastableTitle = cleanerService.cleanTitle(currentSong);
		
		//assert
		LOGGER.debug("Title cleaned : " + broadcastableTitle);
	}
	
	/**
	 * Test cleaner service querying server
	 */
	@Test
	public void testCleanerServiceRealConditions() {
		CurrentSong currentTitle = currentTitleGrabberService.getCurrentTitle();
		BroadcastableTitle broadcastableTitle = cleanerService.cleanTitle(currentTitle);
		LOGGER.debug("Title cleaned : " + broadcastableTitle);
	}
	
}
