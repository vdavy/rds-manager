/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.AdminBundle;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.constants.GeneralConfigConstants;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.messages.GeneralConfigMessages;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.server.AdminServiceAsync;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.utils.widgets.AjaxLoaderWidget;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.GeneralConfigView;

/**
 * Client factory interface
 * @author vincent
 *
 */
public interface ClientFactory {
	EventBus getEventBus();
	PlaceController getPlaceController();
	AdminBundle getAdminBundle();
	GeneralConfigView getGeneralConfigView();	
	AdminServiceAsync getAdminService();
	GeneralConfigConstants getGeneralConfigConstants();
	GeneralConfigMessages getGeneralConfigMessages();
	AjaxLoaderWidget getAjaxLoaderWidget();
}
