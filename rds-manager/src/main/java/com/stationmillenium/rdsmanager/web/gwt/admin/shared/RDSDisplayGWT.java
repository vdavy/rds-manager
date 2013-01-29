/**
 * 
 */
package com.stationmillenium.rdsmanager.web.gwt.admin.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean containing a RDS display text
 * @author vincent
 *
 */
@SuppressWarnings("serial")
public class RDSDisplayGWT implements Serializable {

	private String artist;
	private String title;
	private Date date;
	private String psCommand;
	private String psCommandReturn;
	private String rtCommand;
	private String rtCommandReturn;
	
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the psCommand
	 */
	public String getPsCommand() {
		return psCommand;
	}
	/**
	 * @param psCommand the psCommand to set
	 */
	public void setPsCommand(String psCommand) {
		this.psCommand = psCommand;
	}
	/**
	 * @return the psCommandReturn
	 */
	public String getPsCommandReturn() {
		return psCommandReturn;
	}
	/**
	 * @param psCommandReturn the psCommandReturn to set
	 */
	public void setPsCommandReturn(String psCommandReturn) {
		this.psCommandReturn = psCommandReturn;
	}
	/**
	 * @return the rtCommand
	 */
	public String getRtCommand() {
		return rtCommand;
	}
	/**
	 * @param rtCommand the rtCommand to set
	 */
	public void setRtCommand(String rtCommand) {
		this.rtCommand = rtCommand;
	}
	/**
	 * @return the rtCommandReturn
	 */
	public String getRtCommandReturn() {
		return rtCommandReturn;
	}
	/**
	 * @param rtCommandReturn the rtCommandReturn to set
	 */
	public void setRtCommandReturn(String rtCommandReturn) {
		this.rtCommandReturn = rtCommandReturn;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RDSDisplayBean [artist=" + artist + ", title=" + title
				+ ", date=" + date + ", psCommand=" + psCommand
				+ ", psCommandReturn=" + psCommandReturn + ", rtCommand="
				+ rtCommand + ", rtCommandReturn=" + rtCommandReturn + "]";
	}
	
}
