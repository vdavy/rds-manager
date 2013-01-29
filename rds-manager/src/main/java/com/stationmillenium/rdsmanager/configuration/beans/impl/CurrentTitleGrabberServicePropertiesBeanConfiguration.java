/**
 * 
 */
package com.stationmillenium.rdsmanager.configuration.beans.impl;

import java.io.File;
import java.io.FileNotFoundException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stationmillenium.rdsmanager.beans.currenttitle.CurrentTitleGrabberProperties;
import com.stationmillenium.rdsmanager.configuration.beans.AbstractPropertiesBeanConfiguration;
import com.stationmillenium.rdsmanager.exceptions.utils.PropertyBeanException;

/**
 * Current title grabber service properties bean configuration
 * @author vincent
 *
 */
@Configuration
public class CurrentTitleGrabberServicePropertiesBeanConfiguration extends AbstractPropertiesBeanConfiguration<CurrentTitleGrabberProperties> {

	//property list
	@NotNull
	@Pattern(regexp = "^https://.+")
	private @Value("${currentTitleGrabber.url}") String url;
	
	@NotNull
	@Size(min = 3)
	private @Value("${currentTitleGrabber.username}") String username;

	@NotNull
	@Size(min = 3)
	private @Value("${currentTitleGrabber.password}") String password;
		
	@NotNull
	@Size(min = 1)
	private @Value("${currentTitleGrabber.trustStorePath}") String trustStorePath;
	
	@NotNull
	@Size(min = 3)
	private @Value("${currentTitleGrabber.trustStorePassword}") String trustStorePassword;
	
	@Override
	protected CurrentTitleGrabberProperties buildBean() {
		CurrentTitleGrabberProperties propertiesBean = new CurrentTitleGrabberProperties();
		propertiesBean.setUrl(url);
		propertiesBean.setUsername(username);
		propertiesBean.setPassword(password);
		propertiesBean.setTrustStorePath(trustStorePath);
		propertiesBean.setTrustStorePassword(trustStorePassword);
		return propertiesBean;
	}
	
	@Override
	protected void propertyCustomChecker() throws PropertyBeanException {
		File file = new File(trustStorePath);
		if (!file.exists())
			throw new PropertyBeanException("trustStorePath", new FileNotFoundException(trustStorePath));
	}
	
	/**
	 * Provide the produced bean
	 * @return the <code>CurrentTitleGrabberProperties</code>
	 */
	@Bean
	@Qualifier("currentTitleGrabberProperties")
	public CurrentTitleGrabberProperties getCurrentTitleGrabberProperties() {
		return assembleBean();
	}
	
}
