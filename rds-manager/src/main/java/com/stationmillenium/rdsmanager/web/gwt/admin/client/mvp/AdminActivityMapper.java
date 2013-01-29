/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.activities.GeneralConfigActivity;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory.ClientFactory;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.places.GeneralConfigPlace;

/**
 * Activity mapper for GWT admin module
 * @author vincent
 *
 */
public class AdminActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	
	/**
	 * Create a new {@link AdminActivityMapper}
	 * @param clientFactory
	 */
	public AdminActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;		
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.activity.shared.ActivityMapper#getActivity(com.google.gwt.place.shared.Place)
	 */
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof GeneralConfigPlace)
			return new GeneralConfigActivity(clientFactory);
		else
			return null;
			
	}

}
