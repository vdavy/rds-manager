/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.services;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.beans.currenttitle.CurrentTitleGrabberProperties;
import com.stationmillenium.rdsmanager.beans.mainconfig.MainConfigProperties;
import com.stationmillenium.rdsmanager.beans.rs232.RDSDisplayManagerProperties;

/**
 * Test property loading and validation 
 * @author vincent
 *
 */
public class TestPropertiesChecker {

	//set up logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TestPropertiesChecker.class);
	
	//the configuration for the current title grabber
	@Autowired
	private CurrentTitleGrabberProperties currentTitleGrabberProperties;
	
	//the configuration for the rds display manager
	@Autowired
	private RDSDisplayManagerProperties rdsDisplayManagerProperties;
	
	//the main configuration
	@Autowired
	private MainConfigProperties mainConfigProperties;
	
	/**
	 * Test current title grabber properties
	 */
	@Test
	public void testCurrentTitleGrabberProperties() {
		LOGGER.debug(currentTitleGrabberProperties.toString());
	}
	
	/**
	 * Test RDS display manager properties
	 */
	@Test
	public void testRDSDisplayManagerProperties() {
		LOGGER.debug(rdsDisplayManagerProperties.toString());
	}
	
	/**
	 * Test main configuration properties
	 */
	@Test
	public void testMainConfigProperties() {
		LOGGER.debug(mainConfigProperties.toString());
	}
	
}
