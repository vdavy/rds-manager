/**
 * 
 */
package com.stationmillenium.rdsmanager.services.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;

import com.stationmillenium.rdsmanager.dto.utils.BeanValidationResults;

/**
 * Bean validator with convenient method
 * @author vincent
 *
 */
@Service
public class ValidatorService {

	/**
	 * Do bean validation
	 * @param objectToValidate object to validate
	 * @return the {@link BeanValidationResults} containing validation results
	 */
	public <V>  BeanValidationResults validateBean(V objectToValidate) {
		//process validation
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validatorService = factory.getValidator();
		Set<ConstraintViolation<V>> constraintViolations = validatorService.validate(objectToValidate);
		
		//process errors
		List<String> errorList = new ArrayList<>();
		String beanName = null;
		for (ConstraintViolation<V> constraint : constraintViolations) {
			String error = constraint.getPropertyPath() + " : " + constraint.getMessage()  + " (invalid value : " + constraint.getInvalidValue() +")";
			errorList.add(error);
			
			if (beanName == null) //save bean name
				beanName = constraint.getRootBeanClass().getName();
		}
		
		//process return
		BeanValidationResults results = new BeanValidationResults();
		results.setBeanName(beanName);
		results.setErrors(errorList);
		results.setValidBean(errorList.size() == 0);
		
		return results;
	}
	
}
