/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.view.impl;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory.ClientFactory;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.view.GeneralConfigView;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;

/**
 * Implementation of the {@link GeneralConfigView}
 * @author vincent
 *
 */
public class GeneralConfigViewImpl extends AbstractMessageView implements GeneralConfigView {

	//logger
	private static final Logger LOGGER = Logger.getLogger(GeneralConfigViewImpl.class.getName());
	private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
	
	//ui binder
	private static GeneralConfigViewImplUiBinder uiBinder = GWT.create(GeneralConfigViewImplUiBinder.class);
	interface GeneralConfigViewImplUiBinder extends UiBinder<Widget, GeneralConfigViewImpl> {}

	//fields
	//top label
	@UiField Label messageLabel;
	
	//polling service
	@UiField Image mainServiceImage;
	@UiField Label mainServiceText;
	@UiField CheckBox mainServiceCheckbox;

	//current title
	@UiField Label psDisplayText;
	@UiField Label rtDisplayText;
	
	//history
	@UiField Label rdsDisplayGridCount;
	@UiField(provided = true) DataGrid<RDSDisplayGWT> rdsDisplayGrid = new DataGrid<RDSDisplayGWT>();
	@UiField(provided = true) SimplePager rdsDisplayGridPager;
	
	
	//local instances
	private Presenter presenter;
	private ClientFactory clientFactory;
	private ListDataProvider<RDSDisplayGWT> listDataProvider = new ListDataProvider<RDSDisplayGWT>();
	private ListHandler<RDSDisplayGWT> sortHandler = new ListHandler<RDSDisplayGWT>(listDataProvider.getList()); //add sorting		
	
	/**
	 * Create a new {@link GeneralConfigViewImpl}
	 */
	public GeneralConfigViewImpl(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;		
		rdsDisplayDatagridInit(clientFactory);		
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Init the datagrid
	 * @param clientFactory the client factory
	 */
	private void rdsDisplayDatagridInit(ClientFactory clientFactory) {
		//init the datagrid
		//artist column
		TextColumn<RDSDisplayGWT> artistColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return object.getArtist();
			}
		};
		artistColumn.setSortable(true);
		rdsDisplayGrid.addColumn(artistColumn, clientFactory.getGeneralConfigConstants().getArtistColumn());
		sortHandler.setComparator(artistColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getArtist() != null) 
						&& (arg1.getArtist() != null))
					return arg0.getArtist().compareTo(arg1.getArtist());
				else
					return -1;
			}
		});
		
		//title column
		TextColumn<RDSDisplayGWT> titleColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return object.getTitle();
			}
		};
		titleColumn.setSortable(true);
		rdsDisplayGrid.addColumn(titleColumn, clientFactory.getGeneralConfigConstants().getTitleColumn());
		sortHandler.setComparator(titleColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getTitle() != null) 
						&& (arg1.getTitle() != null))
					return arg0.getTitle().compareTo(arg1.getTitle());
				else
					return -1;
			}
		});
		
		//date column
		TextColumn<RDSDisplayGWT> dateColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return DATE_FORMAT.format(object.getDate());
			}
		};
		dateColumn.setSortable(true);
		rdsDisplayGrid.addColumn(dateColumn, clientFactory.getGeneralConfigConstants().getDateColumn());
		sortHandler.setComparator(dateColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getDate() != null) 
						&& (arg1.getDate() != null))
					return arg0.getDate().compareTo(arg1.getDate());
				else
					return -1;
			}
		});
		
		//ps command column
		TextColumn<RDSDisplayGWT> psCommandColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return object.getPsCommand();
			}
		};
		psCommandColumn.setSortable(true);
		rdsDisplayGrid.addColumn(psCommandColumn, clientFactory.getGeneralConfigConstants().getPSCommandColumn());
		sortHandler.setComparator(psCommandColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getPsCommand() != null) 
						&& (arg1.getPsCommand() != null))
					return arg0.getPsCommand().compareTo(arg1.getPsCommand());
				else
					return -1;
			}
		});
		
		//ps command return column
		TextColumn<RDSDisplayGWT> psCommandReturnColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return object.getPsCommandReturn();
			}
		};
		psCommandReturnColumn.setSortable(true);
		rdsDisplayGrid.addColumn(psCommandReturnColumn, clientFactory.getGeneralConfigConstants().getPSCommandReturnColumn());
		sortHandler.setComparator(psCommandReturnColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getPsCommandReturn() != null) 
						&& (arg1.getPsCommandReturn() != null))
					return arg0.getPsCommandReturn().compareTo(arg1.getPsCommandReturn());
				else
					return -1;
			}
		});
		
		//rt command column
		TextColumn<RDSDisplayGWT> rtCommandColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return object.getRtCommand();
			}
		};
		rtCommandColumn.setSortable(true);
		rdsDisplayGrid.addColumn(rtCommandColumn, clientFactory.getGeneralConfigConstants().getRTCommandColumn());
		sortHandler.setComparator(rtCommandColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getRtCommand() != null) 
						&& (arg1.getRtCommand() != null))
					return arg0.getRtCommand().compareTo(arg1.getRtCommand());
				else
					return -1;
			}
		});
		
		//rt command return column
		TextColumn<RDSDisplayGWT> rtCommandReturnColumn = new TextColumn<RDSDisplayGWT>() {			
			@Override
			public String getValue(RDSDisplayGWT object) {
				return object.getRtCommandReturn();
			}
		};
		rtCommandReturnColumn.setSortable(true);
		rdsDisplayGrid.addColumn(rtCommandReturnColumn, clientFactory.getGeneralConfigConstants().getRTCommandReturnColumn());
		sortHandler.setComparator(rtCommandReturnColumn, new Comparator<RDSDisplayGWT>() {			
			@Override
			public int compare(RDSDisplayGWT arg0, RDSDisplayGWT arg1) {
				if ((arg0 != null) && (arg1 != null)
						&& (arg0.getRtCommandReturn() != null) 
						&& (arg1.getRtCommandReturn() != null))
					return arg0.getRtCommandReturn().compareTo(arg1.getRtCommandReturn());
				else
					return -1;
			}
		});
			
		//pager
		rdsDisplayGridPager = new SimplePager(TextLocation.CENTER, false, true); //pagination
		rdsDisplayGridPager.setDisplay(rdsDisplayGrid);
		
		//data source connection
		listDataProvider.addDataDisplay(rdsDisplayGrid);
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;		
	}

	@Override
	protected ClientFactory getClientFactory() {
		return clientFactory;
	}
	
	@Override
	protected Label getMessageLabel() {
		return messageLabel;
	}
	
	@Override
	public void setMessageLabelTextAndStyle(String text, MessageLabelStyle style) {
		super.setMessageLabelTextAndStyle(text, style);
	}
	
	@Override
	public void setMainServiceInformation(boolean activeService, String associatedText) {
		setImageAndText(activeService, associatedText, mainServiceImage, mainServiceText);
	}
	
	/**
	 * Set image and label with predefined image
	 * @param imageBoolean the boolean value to set the bulled
	 * @param text the text to display in the label
	 * @param imageToSet the image to set
	 * @param labelToSet the label to set
	 */
	private void setImageAndText(boolean imageBoolean, String text, Image imageToSet, Label labelToSet) {
		LOGGER.fine("Set " + imageBoolean + " to " + imageToSet + " - Set " + text + " to " + labelToSet);
		if (imageBoolean) //set image
			imageToSet.setUrl(clientFactory.getAdminBundle().greenBullet().getSafeUri());
		else
			imageToSet.setUrl(clientFactory.getAdminBundle().redBullet().getSafeUri());
		
		labelToSet.setText(text); //set label
	}

	@Override
	public void setPSText(String text) {
		psDisplayText.setText(SafeHtmlUtils.htmlEscape(text));
	}
	
	@Override
	public void setRTText(String text) {
		rtDisplayText.setText(SafeHtmlUtils.htmlEscape(text));
	}
	
	@Override
	public void setMainServiceCheckboxChecked(boolean checked) {
		mainServiceCheckbox.setValue(checked);
	}
	
	@Override
	public void setMainServiceCheckboxActivation(boolean enabled) {
		mainServiceCheckbox.setEnabled(enabled);
	}
	
	/**
	 * Handles value change of main service checkbox
	 * @param event the event
	 */
	@UiHandler("mainServiceCheckbox")
	public void onValueChangedPollingServiceCheckbox(ValueChangeEvent<Boolean> event) {
		presenter.onClickMainServiceCheckbox(event.getValue());
	}

	@Override
	public void setRDSDisplayList(List<RDSDisplayGWT> listToDisplay) {
		listDataProvider.setList((List<RDSDisplayGWT>) listToDisplay);		
		sortHandler.setList(listDataProvider.getList());
		rdsDisplayGrid.setPageStart(0);
		rdsDisplayGridCount.setText(clientFactory.getGeneralConfigMessages().getRDSDisplayCount(listToDisplay.size()));
	}
	
	/**
	 * Handle clicks on refresh history button
	 * @param event the event
	 */
	@UiHandler("refreshButton")
	public void onRefreshHistoryClick(ClickEvent event) {
		LOGGER.fine("Click on refresh button");
		presenter.refreshHistoryList();
	}
	
}
