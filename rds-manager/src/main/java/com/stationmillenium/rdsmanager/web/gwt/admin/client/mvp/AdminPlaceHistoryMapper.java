/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.places.GeneralConfigPlace;

/**
 * Admin GWT module place history mapper
 * @author vincent
 *
 */
@WithTokenizers({
	GeneralConfigPlace.Tokenizer.class
})
public interface AdminPlaceHistoryMapper extends PlaceHistoryMapper {
	//nothing to add
}
