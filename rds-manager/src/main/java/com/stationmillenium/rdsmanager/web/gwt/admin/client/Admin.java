/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory.ClientFactory;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.mvp.AdminActivityMapper;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.mvp.AdminPlaceHistoryMapper;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.places.GeneralConfigPlace;

/**
 * Entry point of GWT admin module
 * @author vincent
 *
 */
public class Admin implements EntryPoint {

	private SimplePanel appWidget = new SimplePanel();
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	
	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	@Override
	public void onModuleLoad() {
		EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

	    // Start ActivityManager for the main widget with our ActivityMapper
	    ActivityMapper activityMapper = new AdminActivityMapper(clientFactory);
	    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
	    activityManager.setDisplay(appWidget);

	    // Start PlaceHistoryHandler with our PlaceHistoryMapper
	    AdminPlaceHistoryMapper historyMapper = GWT.create(AdminPlaceHistoryMapper.class);
	    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
	    historyHandler.register(placeController, eventBus, new GeneralConfigPlace());
	    
	    // build UI
	    RootPanel.get().add(appWidget);
	   
	   	// Goes to the place represented on URL else default place
	    historyHandler.handleCurrentHistory();
	    
	    //inject css
	    clientFactory.getAdminBundle().playlistExtract().ensureInjected();
	}

}
