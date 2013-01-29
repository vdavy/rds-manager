/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.view.impl;

import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.clientfactory.ClientFactory;

/**
 * Abstract class to add support to a message label
 * @author vincent
 *
 */
public abstract class AbstractMessageView  extends Composite {

	/**
	 * Available styles for message label
	 * @author vincent
	 *
	 */
	public static enum MessageLabelStyle {
		DEFAULT,
		GREEN,
		RED;
	}

	protected void setMessageLabelTextAndStyle(String text, MessageLabelStyle style) {
		getMessageLabel().setText(text); //set text
		switch (style) { //set style
		case DEFAULT:
			getMessageLabel().setStyleName(getClientFactory().getAdminBundle().playlistExtract().messageLabelDefault());
			break;

		case GREEN:
			getMessageLabel().setStyleName(getClientFactory().getAdminBundle().playlistExtract().messageLabelGreen());
			break;

		case RED:
			getMessageLabel().setStyleName(getClientFactory().getAdminBundle().playlistExtract().messageLabelRed());
			break;
		}
	}

	/**
	 * Get the message label
	 * @return the {@link Label}
	 */
	@Ignore
	protected abstract Label getMessageLabel();

	/**
	 * Get the client fatory
	 * @return the client factory {@link ClientFactory}
	 */
	protected abstract ClientFactory getClientFactory();

}
