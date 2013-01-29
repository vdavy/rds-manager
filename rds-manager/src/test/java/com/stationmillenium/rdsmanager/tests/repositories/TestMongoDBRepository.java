/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.repositories;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.dto.documents.RDSDisplay;
import com.stationmillenium.rdsmanager.dto.documents.subs.RS232Commands;
import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.repositories.MongoDBRepository;

/**
 * Test the mongo DB repository
 * @author vincent
 *
 */
public class TestMongoDBRepository {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(TestMongoDBRepository.class);
	
	//repository to test
	@Autowired
	private MongoDBRepository dbRepository;
	
	/**
	 * Test insertion into DB
	 */
	@Test
	public void testInsert() {
		//log into db
		RDSDisplay rdsDisplayDocument = new RDSDisplay();
		rdsDisplayDocument.setBroadcastableTitle(new BroadcastableTitle());
		rdsDisplayDocument.getBroadcastableTitle().setArtist("ARTIST");
		rdsDisplayDocument.getBroadcastableTitle().setTitle("TITLE");
		rdsDisplayDocument.setDate(Calendar.getInstance().getTime());
		rdsDisplayDocument.setRs232Commands(new RS232Commands());
		rdsDisplayDocument.getRs232Commands().setPsCommand("Test PS command");
		rdsDisplayDocument.getRs232Commands().setPsCommandReturn("+");
		rdsDisplayDocument.getRs232Commands().setRtCommand("Test RT Command");
		rdsDisplayDocument.getRs232Commands().setRtCommandReturn("+");
		
		dbRepository.insertRDSDisplay(rdsDisplayDocument);
	}
	
	/**
	 * Test the read
	 */
	@Test
	public void testRead() {
		RDSDisplay rdsDisplay = dbRepository.getLastInserted();
		LOGGER.debug("Last record found : " + rdsDisplay);		
	}
	
	/**
	 * Test the read of lastest 
	 */
	@Test
	public void testGetLatestDisplays() {
		List<RDSDisplay> rdsDisplays = dbRepository.getLatestDisplays();
		LOGGER.debug("List of records found : " + rdsDisplays);		
	}
	
}
