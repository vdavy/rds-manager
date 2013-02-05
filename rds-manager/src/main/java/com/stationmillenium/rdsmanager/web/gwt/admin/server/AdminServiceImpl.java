/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stationmillenium.rdsmanager.dto.documents.RDSDisplay;
import com.stationmillenium.rdsmanager.repositories.MongoDBRepository;
import com.stationmillenium.rdsmanager.services.MainService;
import com.stationmillenium.rdsmanager.web.gwt.admin.client.server.AdminService;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;

/**
 * Service impl for the admin GWT module
 * @author vincent
 *
 */
@SuppressWarnings("serial")
@Configurable
@WebServlet("/admin/service")
public class AdminServiceImpl extends RemoteServiceServlet implements AdminService {
	
	//main service
	@Autowired
	private MainService mainService;
	
	//mongo db repository
	@Autowired
	private MongoDBRepository dbRepository;
	
	//dozer for bean conversion
	@Autowired
	private Mapper mapper;

	@Override
	public boolean getRDSManagerStatus() {
		return mainService.isServiceEnabled();
	}

	@Override
	public void setRDSManagerStatus(boolean enabled) {
		mainService.setServiceEnabled(enabled);
	}

	@Override
	public RDSDisplayGWT getCurrentTitle() {
		RDSDisplay rdsDisplay = dbRepository.getLastInserted();
		RDSDisplayGWT rdsDisplayGWT = null;
		if (rdsDisplay != null)
			rdsDisplayGWT = mapper.map(rdsDisplay, RDSDisplayGWT.class);
		else 
			rdsDisplayGWT = new RDSDisplayGWT();		
		return rdsDisplayGWT;
	}

	@Override
	public List<RDSDisplayGWT> getLastDisplays() {
		List<RDSDisplay> rdsDisplays = dbRepository.getLatestDisplays();
		
		//conversion
		List<RDSDisplayGWT> displayGWTs = new ArrayList<>();
		for (RDSDisplay display : rdsDisplays) {
			RDSDisplayGWT displayGWT = mapper.map(display, RDSDisplayGWT.class);
			displayGWTs.add(displayGWT);
		}
		
		return displayGWTs;
	}
		
	
	
}
