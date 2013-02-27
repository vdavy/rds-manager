/**
 * 
 */
package com.stationmillenium.rdsmanager.beans.rs232;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.stationmillenium.rdsmanager.beans.interfaces.PropertyBeanInterface;

/**
 * RS232 wire properties
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
public class RS232Properties implements PropertyBeanInterface {

	private String portName;
	private int speed;
	private boolean virtualMode;
	private long waitingTime;
	private int waitingTries;
	private String initCommand;
	private String initCommandReturn;
	
}
