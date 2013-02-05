/**
 * 
 */
package com.stationmillenium.rdsmanager.configuration.beans.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stationmillenium.rdsmanager.beans.rs232.RDSDisplayManagerProperties;
import com.stationmillenium.rdsmanager.configuration.beans.AbstractPropertiesBeanConfiguration;
import com.stationmillenium.rdsmanager.exceptions.utils.PropertyBeanException;

/**
 * RDS display manager service properties bean configuration
 * @author vincent
 *
 */
@Configuration
public class RDSDisplayManagerServicePropertiesBeanConfiguration extends AbstractPropertiesBeanConfiguration<RDSDisplayManagerProperties> {

	//property list
	@NotNull
	@Size(min = 1)
	private @Value("${rdsDisplayManager.psCommandPrefix}") String psCommandPrefix;
	
	@NotNull
	@Size(min = 1)
	private @Value("${rdsDisplayManager.rtCommandPrefix}") String rtCommandPrefix;

	@Size(max = 8)
	@Pattern(regexp = "(^[A-Z]{1}[A-Z ]*[A-Z]{1}$)*")
	private @Value("${rdsDisplayManager.psIdle}") String psIdle;
		
	@NotNull
	@Size(max = 64)
	@Pattern(regexp = "^[\\p{Alnum}\\p{Blank}\\.\\-]+$")
	private @Value("${rdsDisplayManager.rtIdle}") String rtIdle;
	
	@NotNull
	@Pattern(regexp = "^\\p{Digit}+")
	private @Value("${rdsDisplayManager.maxLength}") String maxLength;
	
	@NotNull
	@Size(min = 1, max = 1)
	private @Value("${rdsDisplayManager.rtSeparator}") String rtSeparator;
	
	@NotNull
	@Size(min = 1, max = 5)
	private @Value("${rdsDisplayManager.psCommandReturn}") String psCommandReturn;
	
	@NotNull
	@Size(min = 1, max = 5)
	private @Value("${rdsDisplayManager.rtCommandReturn}") String rtCommandReturn;
	
	@NotNull
	@Size(min = 1, max = 50)
	private @Value("${rdsDisplayManager.virtualModeReturnText}") String virtualModeReturnText;
	
	@NotNull
	@Size(min = 1)
	private @Value("${rdsDisplayManager.commandTerminaison}") String commandTerminaison;
	
	@NotNull
	@Size(min = 1)
	private @Value("${rdsDisplayManager.psInitCommand}") String psInitCommand;
	
	@NotNull
	@Size(min = 1)
	private @Value("${rdsDisplayManager.rtInitCommand}") String rtInitCommand;
		
	//instances
	private int maxLengthInt;
	
	@Override
	protected RDSDisplayManagerProperties buildBean() {
		RDSDisplayManagerProperties propertiesBean = new RDSDisplayManagerProperties();
		propertiesBean.setPsCommandPrefix(psCommandPrefix);
		propertiesBean.setPsIdle(psIdle);
		propertiesBean.setRtCommandPrefix(rtCommandPrefix);
		propertiesBean.setRtIdle(rtIdle);
		propertiesBean.setMaxLength(maxLengthInt);
		propertiesBean.setRtSeparator(rtSeparator);
		propertiesBean.setPsCommandReturn(psCommandReturn);
		propertiesBean.setRtCommandReturn(rtCommandReturn);
		propertiesBean.setVirtualModeReturnText(virtualModeReturnText);
		propertiesBean.setCommandTerminaison(commandTerminaison);
		propertiesBean.setPsInitCommand(psInitCommand);
		propertiesBean.setRtInitCommand(rtInitCommand);
		return propertiesBean;
	}
	
	@Override
	protected void propertyCustomChecker() throws PropertyBeanException {
		try { //convert maxLength into number
			maxLengthInt = Integer.parseInt(maxLength);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("maxLength", e);
		}
	}
	
	/**
	 * Provide the produced bean
	 * @return the <code>CurrentTitleGrabberProperties</code>
	 */
	@Bean
	@Qualifier("rdsDisplayManagerProperties")
	public RDSDisplayManagerProperties getRDSDisplayManagerProperties() {
		return assembleBean();
	}
	
}
