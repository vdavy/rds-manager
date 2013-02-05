/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory.ClientFactory;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.GeneralConfigView;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.GeneralConfigView.Presenter;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.impl.AbstractMessageView.MessageLabelStyle;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;

/**
 * Activity for the main configuration
 * @author vincent
 *
 */
public class GeneralConfigActivity extends AbstractActivity implements Presenter {

	//logger
	private static final Logger LOGGER = Logger.getLogger(GeneralConfigActivity.class.getName());
	
	//local instances
	private ClientFactory clientFactory;
	private Timer timer;
		
	/**
	 * Create a new {@link GeneralConfigActivity}
	 * @param clientFactory the client factory
	 */
	public GeneralConfigActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		//set up timer
		timer = new Timer() {			
			@Override
			public void run() {
				getCurrentTitle(); //get the current title
			}
		};
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//set up display
		GeneralConfigView view = clientFactory.getGeneralConfigView();
		view.setPresenter(this);
		panel.setWidget(view);
		
		//set the display information
		getServiceStatus();
		getCurrentTitle();
		refreshHistoryList();
		
		//start timer
		timer.scheduleRepeating(2000);
	}
	
	@Override
	public void onStop() {
		LOGGER.fine("Stop timer");
		timer.cancel();
	}

	@Override
	public void onClickMainServiceCheckbox(boolean value) {
		LOGGER.fine("Change main service status : " + value);
		clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getServiceUpdate(), MessageLabelStyle.DEFAULT);
		clientFactory.getGeneralConfigView().setMainServiceCheckboxActivation(false);
		clientFactory.getAdminService().setRDSManagerStatus(value, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getServiceStatusChangeOK(), MessageLabelStyle.GREEN);
				clientFactory.getGeneralConfigView().setMainServiceCheckboxActivation(true);				
				getServiceStatus();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				LOGGER.log(Level.WARNING, "Error while changing main service status", caught);
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getServiceStatusChangeError(), MessageLabelStyle.RED);
			}
		});
	}

	/**
	 * Get and set on view the service status
	 */
	private void getServiceStatus() {
		LOGGER.fine("Get the service status");
		clientFactory.getGeneralConfigView().setMainServiceCheckboxActivation(false);
		clientFactory.getAdminService().getRDSManagerStatus(new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if (result != null) { 
					LOGGER.fine("Received status :" + result);
					
					//deal with polling status case
					clientFactory.getGeneralConfigView().setMainServiceCheckboxActivation(true);
					clientFactory.getGeneralConfigView().setMainServiceCheckboxChecked(result);
					String pollingServiceStatusText = (result) 
							? clientFactory.getGeneralConfigConstants().getServiceEnabled() 
							: clientFactory.getGeneralConfigConstants().getServiceDisabled();
					clientFactory.getGeneralConfigView().setMainServiceInformation(result, pollingServiceStatusText);
															
				} else {
					LOGGER.warning("Services status DTO is null");
					clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getServicesStatusError(), MessageLabelStyle.RED);
				}					
			}
			
			@Override
			public void onFailure(Throwable caught) {
				LOGGER.log(Level.WARNING, "Error while getting the service status", caught);
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getServicesStatusError(), MessageLabelStyle.RED);
			}
		});
	}

	/**
	 * Get the current title
	 */
	private void getCurrentTitle() {
		LOGGER.fine("Get the current title");
		clientFactory.getAdminService().getCurrentTitle(new AsyncCallback<RDSDisplayGWT>() {
			
			@Override
			public void onSuccess(RDSDisplayGWT result) {
				LOGGER.fine("Current title : " + result);
				clientFactory.getGeneralConfigView().setPSText(displayRDSText(result.getPsCommand()));
				clientFactory.getGeneralConfigView().setRTText(displayRDSText(result.getRtCommand()));
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getReadyState(), MessageLabelStyle.DEFAULT);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				LOGGER.log(Level.WARNING, "Error while getting the current title", caught);
				clientFactory.getGeneralConfigView().setPSText("");
				clientFactory.getGeneralConfigView().setRTText("");
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getCurrentTitleError(), MessageLabelStyle.RED);
			}
		});
	}
	
	/**
	 * Format a RDS text from RDS command
	 * @param textToDisplay the command as string
	 * @return the formatted text
	 */
	private String displayRDSText(String textToDisplay) {
		if ((textToDisplay != null) && (textToDisplay.length() > 0)) {
			int indexPosition = textToDisplay.indexOf("=");
			if ((textToDisplay.indexOf("=") > -1) && (indexPosition < textToDisplay.length() -1)) {
				return textToDisplay.substring(indexPosition +1);
			} else
				return clientFactory.getGeneralConfigConstants().getDefaultRDSText();
		} else 
			return "";
	}
			
	@Override
	public void refreshHistoryList() {
		LOGGER.fine("Refresh the history list");
		clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getHistoryRefreshing(), MessageLabelStyle.DEFAULT);
		clientFactory.getAdminService().getLastDisplays(new AsyncCallback<List<RDSDisplayGWT>>() {

			@Override
			public void onSuccess(List<RDSDisplayGWT> result) {
				LOGGER.fine("Current title : " + result);
				clientFactory.getGeneralConfigView().setRDSDisplayList(result);
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getHistoryRefresh(), MessageLabelStyle.GREEN);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				LOGGER.log(Level.WARNING, "Error while getting the history list", caught);
				clientFactory.getGeneralConfigView().setRDSDisplayList(new ArrayList<RDSDisplayGWT>());
				clientFactory.getGeneralConfigView().setMessageLabelTextAndStyle(clientFactory.getGeneralConfigConstants().getHistoryRefreshError(), MessageLabelStyle.RED);
			}
		});		
	}
	
}
