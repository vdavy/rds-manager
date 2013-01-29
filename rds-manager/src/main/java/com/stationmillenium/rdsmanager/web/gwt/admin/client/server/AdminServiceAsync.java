/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.server;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;


/**
 * Service async for the admin GWT module
 * @author vincent
 *
 */
public interface AdminServiceAsync {

	void getCurrentTitle(AsyncCallback<RDSDisplayGWT> callback);
	void getLastDisplays(AsyncCallback<List<RDSDisplayGWT>> callback);
	void getRDSManagerStatus(AsyncCallback<Boolean> callback);
	void setRDSManagerStatus(boolean enabled, AsyncCallback<Void> callback);
	
}
