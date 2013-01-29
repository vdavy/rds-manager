package com.stationmillenium.rdsmanager.beans.mainconfig;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.stationmillenium.rdsmanager.beans.interfaces.PropertyBeanInterface;

/**
 * Bean containing the main config properties
 * @author vincent
 *
 */
@RooToString
@RooJavaBean
public class MainConfigProperties implements PropertyBeanInterface {

	private int idleModePauseTime;
	private int titleModePauseTime;
	private int maxCountDisplayList;
	
}
