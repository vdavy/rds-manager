/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * GWT admin module bundle
 * @author vincent
 *
 */
public interface AdminBundle extends ClientBundle {

	@Source("green-bullet.png")
	ImageResource greenBullet();

	@Source("red-bullet.png")
	ImageResource redBullet();

	@Source("playlist-extract.css")
	PlaylistExtract playlistExtract();
	
	public interface PlaylistExtract extends CssResource {
		String messageLabelRed();
		String messageLabelDefault();
		String messageLabelGreen();
	}

}
