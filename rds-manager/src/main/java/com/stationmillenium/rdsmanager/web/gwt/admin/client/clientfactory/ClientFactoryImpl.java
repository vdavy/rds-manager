/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.AdminBundle;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.constants.GeneralConfigConstants;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.messages.GeneralConfigMessages;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.server.AdminService;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.server.AdminServiceAsync;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.utils.widgets.AjaxLoaderWidget;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.GeneralConfigView;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.impl.GeneralConfigViewImpl;

/**
 * GWT admin module client factory impl
 * @author vincent
 *
 */
public class ClientFactoryImpl implements ClientFactory {

	private AjaxLoaderWidget ajaxLoaderWidget = new AjaxLoaderWidget();
	private EventBus eventBus = new SimpleEventBus();
	private PlaceController placeController = new PlaceController(eventBus);
	private AdminBundle adminBundle = GWT.create(AdminBundle.class);
	private GeneralConfigConstants generalConfigConstants = GWT.create(GeneralConfigConstants.class);
	private GeneralConfigView generalConfigView = new GeneralConfigViewImpl(this);
	private AdminServiceAsync adminService = GWT.create(AdminService.class);
	private GeneralConfigMessages generalConfigMessages = GWT.create(GeneralConfigMessages.class);	
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public AdminBundle getAdminBundle() {
		return adminBundle;
	}

	@Override
	public GeneralConfigView getGeneralConfigView() {
		return generalConfigView;
	}
	
	@Override
	public GeneralConfigMessages getGeneralConfigMessages() {
		return generalConfigMessages;
	}
	
	@Override
	public AdminServiceAsync getAdminService() {
		return adminService;
	}
	
	@Override
	public GeneralConfigConstants getGeneralConfigConstants() {
		return generalConfigConstants;
	}
	
	@Override
	public AjaxLoaderWidget getAjaxLoaderWidget() {
		return ajaxLoaderWidget;
	}

}
