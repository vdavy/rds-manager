/**
 * 
 */
package com.stationmillenium.rdsmanager.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.stationmillenium.rdsmanager.beans.mainconfig.MainConfigProperties;
import com.stationmillenium.rdsmanager.dto.documents.RDSDisplay;

/**
 * Repository to access MongoDB
 * @author vincent
 *
 */
@Repository
public class MongoDBRepository {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBRepository.class);
	
	//main config
	@Autowired
	private MainConfigProperties configProperties;
	
	//template to access mongo db
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * Insert a RDS display into DB
	 * @param display the document to insert
	 */
	public void insertRDSDisplay(RDSDisplay display) {
		LOGGER.debug("Insert the RDS display : " + display);
		mongoTemplate.insert(display);
	}

	/**
	 * Get the last inserted document
	 * @return the document
	 */
	public RDSDisplay  getLastInserted() {
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "date"));
		RDSDisplay display = mongoTemplate.findOne(query, RDSDisplay.class);
		LOGGER.debug("Found document : " + display);
		return display;
	}
	
	/**
	 * Get the list of the lastest displays
	 * @return the list of {@link RDSDisplay}
	 */
	public List<RDSDisplay> getLatestDisplays() {
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "date")).limit(configProperties.getMaxCountDisplayList());
		List<RDSDisplay> displayList = mongoTemplate.find(query, RDSDisplay.class);
		LOGGER.debug("Found document list : " + displayList);
		return displayList;
	}
	
}

