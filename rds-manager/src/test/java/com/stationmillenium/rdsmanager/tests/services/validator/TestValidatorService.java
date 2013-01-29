/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.services.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.dto.utils.BeanValidationResults;
import com.stationmillenium.rdsmanager.services.validator.ValidatorService;

/**
 * Test the {@link ValidatorService}
 * @author vincent
 *
 */
public class TestValidatorService {

	private static Logger LOGGER = LoggerFactory.getLogger(TestValidatorService.class);
	
	//service to test
	@Autowired
	private ValidatorService service;
	
	//instances for test
	private BroadcastableTitle  goodTitle = new BroadcastableTitle();
	private BroadcastableTitle  badTitle = new BroadcastableTitle();
	
	@Before
	public void initMock() {
		goodTitle.setArtist("ARTIST ARTIST");
		goodTitle.setTitle("TITLE TITLE");		
		badTitle.setArtist("ARTIST artist");
		badTitle.setTitle("TITLE title");
	}
	
	/**
	 * Test the validator if good validation expected
	 */
	@Test
	public void testGoodValidation() {
		//process
		BeanValidationResults results = service.validateBean(goodTitle);
		
		//assert
		assertNotNull(results);
		assertTrue(results.isValidBean());
		assertEquals(results.getErrors().size(), 0);
		assertNull(results.getBeanName());
	}
	
	/**
	 * Test the validator if bad validation expected
	 */
	@Test
	public void testBadValidation() {
		//process
		BeanValidationResults results = service.validateBean(badTitle);
		
		//assert
		assertNotNull(results);
		assertFalse(results.isValidBean());
		assertNotEquals(results.getErrors().size(), 0);
		assertEquals(results.getBeanName(), BroadcastableTitle.class.getName());
		
		LOGGER.debug("Errors : " + results.getErrors());
	}
	
}
