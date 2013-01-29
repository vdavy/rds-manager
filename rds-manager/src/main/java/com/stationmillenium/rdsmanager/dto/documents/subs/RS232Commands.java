/**
 * 
 */
package com.stationmillenium.rdsmanager.dto.documents.subs;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * RS232 commands sent on wire
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
public class RS232Commands {

	private String psCommand;
	private String psCommandReturn;
	private String rtCommand;
	private String rtCommandReturn;
	
}
