/**
 * 
 */
package com.stationmillenium.rdsmanager.dto.utils;

import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * Bean validation results
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
public class BeanValidationResults {

	private String beanName;
	private boolean validBean;
	private List<String> errors;
	
}
