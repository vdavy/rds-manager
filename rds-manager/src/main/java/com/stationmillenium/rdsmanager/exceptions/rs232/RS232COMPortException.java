/**
 * 
 */
package com.stationmillenium.rdsmanager.exceptions.rs232;

/**
 * Exception on RS232 wire
 * @author vincent
 *
 */
@SuppressWarnings("serial")
public class RS232COMPortException extends Exception {

	/**
	 * Create a new exeception with the message
	 * @param message the message
	 */
	public RS232COMPortException(String message) {
		super(message);
	}
	
}
