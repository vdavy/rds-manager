/**
 * 
 */
package com.stationmillenium.rdsmanager.beans.rs232;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.stationmillenium.rdsmanager.beans.interfaces.PropertyBeanInterface;

/**
 * Properties for the RDS display manager
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
public class RDSDisplayManagerProperties implements PropertyBeanInterface {

	private String psCommandPrefix;
	private String rtCommandPrefix;
	private String psIdle;
	private String rtIdle;
	private int maxLength;
	private String rtSeparator;
	
}
