/**
 * 
 */
package com.stationmillenium.rdsmanager.dto.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.stationmillenium.rdsmanager.dto.documents.subs.RS232Commands;
import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;

/**
 * Class to represent a recorded document in the mongo db
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
@Document
public class RDSDisplay {

	@Id private String id;
	private BroadcastableTitle broadcastableTitle;
	@Indexed private Date date;
	private RS232Commands rs232Commands;
	
}
