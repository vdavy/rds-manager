/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.services.rs232;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.services.rs232.RDSDisplayManagerService;

/**
 * Test the {@link RDSDisplayManagerService}
 * @author vincent
 *
 */
public class TestRDSDisplayManager {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TestRDSDisplayManager.class);
	
	//instances
	private BroadcastableTitle title;
	
	//service to test
	@Autowired
	private RDSDisplayManagerService rdsDisplayManagerService;
	
	@Before
	public void init() {
		title = new BroadcastableTitle();
		title.setArtist("ALICIA KEYS FEAT NICKI MINAJ");
		title.setTitle("GIRL ON FIRE INFERNO VERSION");
	}
	
	/**
	 * Test the {@link RDSDisplayManagerService#displayTitle(com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle)}
	 */
	@Test
	public void testDisplayTitle() {
		rdsDisplayManagerService.displayTitle(title);
	}
	
	/**
	 * Test the {@link RDSDisplayManagerService#displayIdleText()}
	 */
	@Test
	public void testDisplayIdleText() {
		rdsDisplayManagerService.displayIdleText();
	}
	
}
