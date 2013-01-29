/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.messages;

import com.google.gwt.i18n.client.LocalizableResource.Generate;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;
import com.google.gwt.i18n.client.Messages;

/**
 * Messages for admin GWT module general config
 * @author vincent
 *
 */
@GenerateKeys
@Generate(format = "com.google.gwt.i18n.rebind.format.PropertiesFormat")
public interface GeneralConfigMessages extends Messages {

	@DefaultMessage("Ligne affichées : {0}")
	String getRDSDisplayCount(int count);
	
}
