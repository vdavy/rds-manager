/**
 * 
 */
package com.stationmillenium.rdsmanager.tests.services.alertmails;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stationmillenium.rdsmanager.services.alertmails.AlertMailService;

/**
 * Test the {@link AlertMailService}
 * @author vincent
 *
 */
public class TestAlertMailService {

	//service to test
	@Autowired
	private AlertMailService service;
	
	/**
	 * Test COM port error alert
	 */
	@Test
	public void testCOMPortErrorAlert() {
		try {
			throw new Exception("test COM port error");
		} catch(Exception e) {
			service.sendCOMPortErrorAlert(e);
		}
	}
	
	/**
	 * Test RDS return alert
	 */
	@Test
	public void testRDSCoderErrorAlert() {
		try {
			throw new Exception("test RDS coder error");
		} catch(Exception e) {
			service.sendRDSCoderErrorAlert(e);
		}
	}
	
	/**
	 * Test webservice communication alert
	 */
	@Test
	public void testWebserviceCommunicationErrorAlert() {
		try {
			throw new Exception("test webservice communication error error");
		} catch(Exception e) {
			service.sendWebserviceCommunicationErrorAlert(e);
		}
	}
	
}
