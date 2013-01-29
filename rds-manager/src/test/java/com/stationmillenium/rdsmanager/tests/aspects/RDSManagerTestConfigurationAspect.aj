package com.stationmillenium.rdsmanager.tests.aspects;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  Auto configure test classes
 *  @author vincent
 *
 */
public aspect RDSManagerTestConfigurationAspect {
	 
	declare @type: com.stationmillenium.rdsmanager.tests..Test* : @RunWith(SpringJUnit4ClassRunner.class);
	    
	declare @type: com.stationmillenium.rdsmanager.tests..Test* : @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
	
	declare @type: com.stationmillenium.rdsmanager.tests..Test* : @ActiveProfiles("test");
}
