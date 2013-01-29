/**
 * 
 */
package com.stationmillenium.rdsmanager.configuration.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import com.stationmillenium.rdsmanager.beans.interfaces.PropertyBeanInterface;
import com.stationmillenium.rdsmanager.dto.utils.BeanValidationResults;
import com.stationmillenium.rdsmanager.exceptions.utils.PropertyBeanException;
import com.stationmillenium.rdsmanager.services.validator.ValidatorService;

/**
 * Abstract class for bean configuration classes
 * @author vincent
 *
 */
@Configurable
public abstract class AbstractPropertiesBeanConfiguration<T extends PropertyBeanInterface> implements ApplicationContextAware {

	//validator service 
	@Autowired
	private ValidatorService validatorService;
	
	//application context
	private ApplicationContext context;
	private static final Logger LOGGER  =  LoggerFactory.getLogger("Property initialization");
	
	//abstract methods
	/**
	 * Check values of properties
	 * @throws PropertyBeanException if a value is incorrect
	 */
	protected abstract void propertyCustomChecker() throws PropertyBeanException;
	
	/**
	 * Build the output bean with correct values
	 * @return the output bean
	 */
	protected abstract T buildBean();
		
	/**
	 * Assemble the bean : check values and make it
	 * @return the bean
	 */	
	protected T assembleBean() {
		try {
			doBeanValidation(this);
			propertyCustomChecker(); //custom values
			return buildBean();
		} catch (PropertyBeanException e) { //if error
			LOGGER.error("Error during property parsing - app starting stopped", e);
			((AbstractApplicationContext) context).close(); //unload context (stop starting)
			return null; //nothing to return
		}
	}
	
	/**
	 * Do bean validation
	 * @param objectToValidate object to validate
	 * @throws PropertyBeanException if validation error is found
	 */
	private <V>  void doBeanValidation(V objectToValidate) throws PropertyBeanException {
		//process validation
		BeanValidationResults results = validatorService.validateBean(objectToValidate);
		
		if (!results.isValidBean()) //if some errors found
			throw new PropertyBeanException(results.getBeanName(), results.getErrors().toString());
	}
		
	/**
	 * Set up context
	 * @param applicationContext the application context
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
}
