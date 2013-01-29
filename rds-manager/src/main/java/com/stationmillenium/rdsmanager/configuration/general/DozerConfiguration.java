/**
 * 
 */
package com.stationmillenium.rdsmanager.configuration.general;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stationmillenium.rdsmanager.dto.documents.RDSDisplay;
import com.stationmillenium.rdsmanager.dto.title.BroadcastableTitle;
import com.stationmillenium.rdsmanager.schema.currentsong.CurrentSong;
import com.stationmillenium.rdsmanager.web.gwt.admin.shared.RDSDisplayGWT;

/**
 * Configuration class for Dozer mapping
 * @author vincent
 *
 */
@Configuration
public class DozerConfiguration {

	/**
	 * Get the {@link DozerBeanMapper}
	 * @return the <code>DozerBeanMapper</code>
	 */
	@Bean
	public DozerBeanMapper getDozerBeanMapper() {
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.addMapping(mapSongEntityToHistoryDTO());
		return dozerBeanMapper;
	}
	
	/**
	 * Map the {@link SongItem} artist and title to {@link SongHistoryItemDTO}
	 * Played date not mapped 
	 * @return the {@link BeanMappingBuilder}
	 */
	private BeanMappingBuilder mapSongEntityToHistoryDTO() {
		BeanMappingBuilder bmb = new BeanMappingBuilder() {						
			@Override
			protected void configure() {
				mapping(CurrentSong.class, BroadcastableTitle.class).exclude("dateTime")
						.exclude("available")
						.fields("artist", "artist")
						.fields("title", "title");
				
				mapping(RDSDisplay.class, RDSDisplayGWT.class)
						.fields("broadcastableTitle.artist", "artist")
						.fields("broadcastableTitle.title", "title")
						.fields("date", "date")
						.fields("rs232Commands.psCommand", "psCommand")
						.fields("rs232Commands.psCommandReturn", "psCommandReturn")
						.fields("rs232Commands.rtCommand", "rtCommand")
						.fields("rs232Commands.rtCommandReturn", "rtCommandReturn");
			}
		};
		
		return bmb;
	}
}
