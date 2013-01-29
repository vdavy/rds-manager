/**
 * 
 */
package com.stationmillenium.rdsmanager.dto.title;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;


/**
 * Broadcastable title, properly formatted
 * @author vincent
 *
 */
@RooJavaBean
@RooToString
public class BroadcastableTitle {

	@NotNull
	@Size(min = 2)
	@Pattern(regexp = "^[A-Z0-9]{1}[0-9A-Z ]*[0-9A-Z]{1}$")
	private String artist;

	@NotNull
	@Size(min = 2)
	@Pattern(regexp = "^[0-9A-Z]{1}[0-9A-Z ]*[0-9A-Z]{1}$")
	private String title;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BroadcastableTitle other = (BroadcastableTitle) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
