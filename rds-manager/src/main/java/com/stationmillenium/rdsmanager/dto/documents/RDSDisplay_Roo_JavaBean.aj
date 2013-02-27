// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.stationmillenium.rdsmanager.dto.documents;

import com.stationmillenium.rdsmanager.dto.documents.RDSDisplay;
import com.stationmillenium.rdsmanager.dto.documents.subs.RS232Commands;
import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import java.util.Date;

privileged aspect RDSDisplay_Roo_JavaBean {
    
    public String RDSDisplay.getId() {
        return this.id;
    }
    
    public void RDSDisplay.setId(String id) {
        this.id = id;
    }
    
    public BroadcastableTitle RDSDisplay.getBroadcastableTitle() {
        return this.broadcastableTitle;
    }
    
    public void RDSDisplay.setBroadcastableTitle(BroadcastableTitle broadcastableTitle) {
        this.broadcastableTitle = broadcastableTitle;
    }
    
    public Date RDSDisplay.getDate() {
        return this.date;
    }
    
    public void RDSDisplay.setDate(Date date) {
        this.date = date;
    }
    
    public RS232Commands RDSDisplay.getRs232Commands() {
        return this.rs232Commands;
    }
    
    public void RDSDisplay.setRs232Commands(RS232Commands rs232Commands) {
        this.rs232Commands = rs232Commands;
    }
    
}