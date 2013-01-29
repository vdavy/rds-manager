/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.services.currenttitle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.schema.currentsong.CurrentSong;
import com.stationmillenium.rdsmanager.services.currenttitle.CurrentTitleGrabberService;

/**
 * Test the {@link CurrentTitleGrabberService}
 * @author vincent
 *
 */
public class TestCurrentTitleGrabber {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCurrentTitleGrabber.class);
	
	//service to test
	@Autowired
	private CurrentTitleGrabberService currentTitleGrabberService;
	
	/**
	 * Test the {@link CurrentTitleGrabberService#getCurrentTitle()}
	 */
	@Test
	public void testGetCurrentTitle() {
		CurrentSong title = currentTitleGrabberService.getCurrentTitle();
		LOGGER.info("Grabbed title : " + title);
	}
	
}
