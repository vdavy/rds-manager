/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.client.server;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;

/**
 * Service for the admin GWT module
 * @author vincent
 *
 */
@RemoteServiceRelativePath("service")
public interface AdminService extends RemoteService {

	/**
	 * Get the RDS manager status
	 * @return <code>true</code> if service enabled, <code>false</code> if not
	 */
	boolean getRDSManagerStatus();
	
	/**
	 * Set the RDS manager status
	 * @param enabled <code>true</code> to enable, <code>false</code> to disable
	 */
	void setRDSManagerStatus(boolean enabled);

	/**
	 * Get the current title 
	 * @return the current title as {@link RDSDisplayGWT} 
	 */
	RDSDisplayGWT getCurrentTitle();
	
	/**
	 * Get the last displays
	 * @return the list of {@link RDSDisplayGWT}
	 */
	List<RDSDisplayGWT> getLastDisplays();
	
}
