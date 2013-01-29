/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.impl.AbstractMessageView.MessageLabelStyle;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;

/**
 * Interface of the main configuration view
 * @author vincent
 *
 */
public interface GeneralConfigView extends IsWidget {

	/**
	 * Set the presenter
	 * @param presenter the presenter
	 */
	void setPresenter(Presenter presenter);

	/**
	 * Set the message label text and style
	 * @param text the text to display
	 * @param style the style to apply to the text
	 */
	void setMessageLabelTextAndStyle(String text, MessageLabelStyle style);

	/**
	 * Set the main service information
	 * @param activeService if the service is active
	 * @param associatedText the associated text to display with
	 */
	void setMainServiceInformation(boolean activeService, String associatedText);

	/**
	 * Set the main service checkbox checked
	 * @param checked <code>true</code> if checked, <code>false</code> if not
	 */
	void setMainServiceCheckboxChecked(boolean checked);

	/**
	 * Set the main service checkbox enabled
	 * @param enabled <code>true</code> if enabled, <code>false</code>
	 */
	void setMainServiceCheckboxActivation(boolean enabled);
	
	/**
	 * Set the current PS text
	 * @param text the text
	 */
	void setPSText(String text);

	/**
	 * Set the current RT text
	 * @param text the text
	 */
	void setRTText(String text);
	
	/**
	 * Set a list to display
	 * @param listToDisplay the list to display
	 */
	void setRDSDisplayList(List<RDSDisplayGWT> listToDisplay);
	
	/**
	 * Interface for presenter of {@link GeneralConfigView}
	 * @author vincent
	 *
	 */
	public interface Presenter {

		/**
		 * Called when main service checkbox is clicked
		 * @param value the value of the checkbox 
		 */
		void onClickMainServiceCheckbox(boolean value);

		/**
		 * Refresh the history list
		 */
		void refreshHistoryList();
		
	}

}
