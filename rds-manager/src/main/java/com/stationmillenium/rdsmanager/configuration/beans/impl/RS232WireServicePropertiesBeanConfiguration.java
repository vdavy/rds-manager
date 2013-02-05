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

import com.stationmillenium.rdsmanager.beans.rs232.RS232Properties;
import com.stationmillenium.rdsmanager.configuration.beans.AbstractPropertiesBeanConfiguration;
import com.stationmillenium.rdsmanager.exceptions.utils.PropertyBeanException;

/**
 * RS232 wire service properties bean configuration
 * @author vincent
 *
 */
@Configuration
public class RS232WireServicePropertiesBeanConfiguration extends AbstractPropertiesBeanConfiguration<RS232Properties> {

	//property list
	@NotNull
	@Size(min = 1, max = 4)
	private @Value("${rs232Wire.portName}") String portName;
	
	@NotNull
	@Pattern(regexp = "^[\\p{Digit}]+$")
	private @Value("${rs232Wire.speed}") String speed;
	
	@NotNull
	@Pattern(regexp = "^(true|false)$")
	private @Value("${rs232Wire.virtualMode}") String virtualMode;
	
	@NotNull
	@Pattern(regexp = "^[\\p{Digit}]+$")
	private @Value("${rs232Wire.waitingTime}") String waitingTime;
	
	@NotNull
	@Pattern(regexp = "^[\\p{Digit}]+$")
	private @Value("${rs232Wire.waitingTries}") String waitingTries;
	
	//instances
	private int speedInt;
	private boolean virtualModeBool;
	private long waitingTimeLong;
	private int waitingTriesInt;
	
	
	@Override
	protected RS232Properties buildBean() {
		RS232Properties propertiesBean = new RS232Properties();
		propertiesBean.setPortName(portName);
		propertiesBean.setSpeed(speedInt);
		propertiesBean.setVirtualMode(virtualModeBool);
		propertiesBean.setWaitingTime(waitingTimeLong);
		propertiesBean.setWaitingTries(waitingTriesInt);
		return propertiesBean;
	}
	
	@Override
	protected void propertyCustomChecker() throws PropertyBeanException {
		try { //convert maxLength into number
			speedInt = Integer.parseInt(speed);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("speed", e);
		}
		
		//convert virtualMode into boolean
		virtualModeBool = new Boolean(virtualMode);
		
		try { //convert waitingTime into number
			waitingTimeLong = Long.parseLong(waitingTime);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("waitingTime", e);
		}
		
		try { //convert waitingTries into number
			waitingTriesInt = Integer.parseInt(waitingTries);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("waitingTries", e);
		}
	}
	
	/**
	 * Provide the produced bean
	 * @return the <code>RS232Properties</code>
	 */
	@Bean
	@Qualifier("rs232Properties")
	public RS232Properties getRS232Properties() {
		return assembleBean();
	}
	
}
