/**
 * 
 */
package com.stationmillenium.rdsmanager.configuration.beans.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stationmillenium.rdsmanager.beans.mainconfig.MainConfigProperties;
import com.stationmillenium.rdsmanager.configuration.beans.AbstractPropertiesBeanConfiguration;
import com.stationmillenium.rdsmanager.exceptions.utils.PropertyBeanException;

/**
 * Current title grabber service properties bean configuration
 * @author vincent
 *
 */
@Configuration
public class MainConfigPropertiesBeanConfiguration extends AbstractPropertiesBeanConfiguration<MainConfigProperties> {

	//property list
	@NotNull
	@Pattern(regexp = "^\\p{Digit}+")
	private @Value("${mainConfig.idleModePauseTime}") String idleModePauseTime;
	
	@NotNull
	@Pattern(regexp = "^\\p{Digit}+")
	private @Value("${mainConfig.titleModePauseTime}") String titleModePauseTime;
	
	@NotNull
	@Pattern(regexp = "^\\p{Digit}+")
	private @Value("${mainConfig.maxCountDisplayList}") String maxCountDisplayList;
	
	//local instances
	private int idleModePauseTimeInt;
	private int titleModePauseTimeInt;
	private int maxCountDisplayListInt;
	
	@Override
	protected MainConfigProperties buildBean() {
		MainConfigProperties propertiesBean = new MainConfigProperties();
		propertiesBean.setIdleModePauseTime(idleModePauseTimeInt);
		propertiesBean.setTitleModePauseTime(titleModePauseTimeInt);
		propertiesBean.setMaxCountDisplayList(maxCountDisplayListInt);
		return propertiesBean;
	}
	
	@Override
	protected void propertyCustomChecker() throws PropertyBeanException {
		try { //convert idleModePauseTime into number
			idleModePauseTimeInt = Integer.parseInt(idleModePauseTime);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("idleModePauseTime", e);
		}
		
		try { //convert titleModePauseTime into number
			titleModePauseTimeInt = Integer.parseInt(titleModePauseTime);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("titleModePauseTime", e);
		}
		
		try { //convert maxCountDisplayList into number
			maxCountDisplayListInt = Integer.parseInt(maxCountDisplayList);
		} catch(NumberFormatException e) {
			throw new PropertyBeanException("maxCountDisplayList", e);
		}
		
	}
	
	/**
	 * Provide the produced bean
	 * @return the <code>MainConfigProperties</code>
	 */
	@Bean
	@Qualifier("mainConfigProperties")
	public MainConfigProperties getMainConfigProperties() {
		return assembleBean();
	}
	
}
