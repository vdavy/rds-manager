/**
 * 
 */
package com.stationmillenium.rdsmanager.beans.currenttitle;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.stationmillenium.rdsmanager.beans.interfaces.PropertyBeanInterface;

/**
 * Properties for service to get the current title 
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
public class CurrentTitleGrabberProperties implements PropertyBeanInterface {

	private String url;
	private String username;
	private String password;
	private String trustStorePath;
	private String trustStorePassword;
	private int connectionTimeout;
	private int readTimeout;
	private boolean sendMailOnTimeout;
	
}
