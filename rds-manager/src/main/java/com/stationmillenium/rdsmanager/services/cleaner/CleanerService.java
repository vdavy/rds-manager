/**
 * 
 */
package com.stationmillenium.rdsmanager.services.cleaner;

import java.text.Normalizer;

import org.apache.commons.lang3.text.WordUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.dto.utils.BeanValidationResults;
import com.stationmillenium.rdsmanager.schema.currentsong.CurrentSong;
import com.stationmillenium.rdsmanager.services.validator.ValidatorService;

/**
 * Service to clean title from not allowed chars
 * @author vincent
 *
 */
@Service
public class CleanerService {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(CleanerService.class);
	
	//dozer service
	@Autowired
	private Mapper mapper;
	
	//validator service
	@Autowired
	private ValidatorService validatorService;
	
	/**
	 * Clean a title and convert to a broadcastable title
	 * @param titleToClean the title to clean
	 * @return a {@link BroadcastableTitle} 
	 */
	public BroadcastableTitle cleanTitle(CurrentSong titleToClean) {
		if (titleToClean != null) {
			//process clean
			titleToClean.setArtist(cleanString(titleToClean.getArtist())); //clean artist
			titleToClean.setTitle(cleanString(titleToClean.getTitle())); //clean title
			
			//convert
			BroadcastableTitle broadcastableTitle = mapper.map(titleToClean, BroadcastableTitle.class);
			LOGGER.debug("Broadcastable title : " + broadcastableTitle);
			
			//verify
			return verifyBean(broadcastableTitle);		
			
		} else {
			LOGGER.warn("Title to clean is null !");
			return null;
		}
	}

	/**
	 * Verify the broadcastable title is OK
	 * @param broadcastableTitle the title to check
	 * @return the {@link BroadcastableTitle} if OK, <code>null</code> if validation KO
	 */
	private BroadcastableTitle verifyBean(BroadcastableTitle broadcastableTitle) {
		BeanValidationResults validationResult = validatorService.validateBean(broadcastableTitle);
		if (validationResult.isValidBean())
			return broadcastableTitle;
		else {
			LOGGER.warn("Validation errors : " + validationResult.getErrors());
			return null;
		}
	}
	
	/**
	 * Remove all forbidden chars and format text
	 * @param valueToClean the value to clean
	 * @return the cleaned string
	 */
	private String cleanString(String valueToClean)  {
		if (valueToClean != null) {
			
			LOGGER.debug("Value to clean : " + valueToClean);
			String cleanedValue = Normalizer.normalize(valueToClean, Normalizer.Form.NFD).replaceAll("[^\\p{Alnum}\\p{Blank}\\-]", "");
			cleanedValue = cleanedValue.replaceAll("[\\p{Blank}][\\p{Blank}]+", " ");
			cleanedValue = cleanedValue.trim();
            cleanedValue = WordUtils.capitalizeFully(cleanedValue);
			LOGGER.debug("Cleaned value : " + cleanedValue);
			return cleanedValue;
			
		} else {
			LOGGER.warn("Value to clean is null");
			return "";
		}
	}
	
}
