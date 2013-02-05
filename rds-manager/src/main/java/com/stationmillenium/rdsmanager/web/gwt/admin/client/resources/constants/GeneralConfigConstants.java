/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.resources.constants;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.LocalizableResource.Generate;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

/**
 * Constants for admin GWT module general config
 * @author vincent
 *
 */
@GenerateKeys
@Generate(format = "com.google.gwt.i18n.rebind.format.PropertiesFormat")
public interface GeneralConfigConstants extends Constants {

	@DefaultStringValue("Impossible de récupérer le statut du service")
	String getServicesStatusError();
	
	@DefaultStringValue("Service actif")
	String getServiceEnabled();
	
	@DefaultStringValue("Service désactivé")
	String getServiceDisabled();
	
	@DefaultStringValue("Mise à jour en cours du service...")
	String getServiceUpdate();
	
	@DefaultStringValue("Impossible de récupérer le titre en cours")
	String getCurrentTitleError();
	
	@DefaultStringValue("Erreur durant le changement de statut")
	String getServiceStatusChangeError();
	
	@DefaultStringValue("Statut du service changé")
	String getServiceStatusChangeOK();
	
	@DefaultStringValue("Interface prête - en attente...")
	String getReadyState();
	
	@DefaultStringValue("MILENIUM")
	String getDefaultRDSText();
	
	@DefaultStringValue("Artiste")
	String getArtistColumn();
	
	@DefaultStringValue("Titre")
	String getTitleColumn();
	
	@DefaultStringValue("Date")
	String getDateColumn();
	
	@DefaultStringValue("Commande PS")
	String getPSCommandColumn();
	
	@DefaultStringValue("Retour commande PS")
	String getPSCommandReturnColumn();
	
	@DefaultStringValue("Commande RT")
	String getRTCommandColumn();
	
	@DefaultStringValue("Retour commande RT")
	String getRTCommandReturnColumn();
	
	@DefaultStringValue("Impossible de récupérer la liste de l'historique")
	String getHistoryRefreshError();
	
	@DefaultStringValue("Historique mis à jour")
	String getHistoryRefresh();
	
	@DefaultStringValue("Mise  à jour en cours...")
	String getHistoryRefreshing();
	
}
