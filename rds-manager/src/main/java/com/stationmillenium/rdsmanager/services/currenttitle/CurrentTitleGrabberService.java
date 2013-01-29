/**
 * 
 */
package com.stationmillenium.rdsmanager.services.currenttitle;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;

import javax.annotation.PostConstruct;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stationmillenium.rdsmanager.beans.currenttitle.CurrentTitleGrabberProperties;
import com.stationmillenium.rdsmanager.schema.currentsong.CurrentSong;

/**
 * Grabber to get the current title to display
 * @author vincent
 *
 */
@Service
public class CurrentTitleGrabberService {

	//logger
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentTitleGrabberService.class);

	//configuration
	@Autowired
	private CurrentTitleGrabberProperties properties;

	//xml marshallers
	@Autowired
	@Qualifier("oxmCurrentSong")
	private Jaxb2Marshaller oxmCurrentSong;
	
	/**
	 * Set up SSL params
	 */
	@PostConstruct
	public void setSSLParams() {
		if (LOGGER.isDebugEnabled())
			System.setProperty("javax.net.debug", "ssl");
		System.setProperty("javax.net.ssl.trustStore", properties.getTrustStorePath());
		System.setProperty("javax.net.ssl.trustStorePassword", properties.getTrustStorePassword());
	}
	
	/**
	 * Get the current title
	 * @return the current song extracted from server
	 */
	public CurrentSong getCurrentTitle() {
		String xml = getXMLAsString();
		CurrentSong currentTitle = unmarshalllData(xml);
		LOGGER.debug("XML unmarshalled : " + currentTitle);
		return currentTitle;
	}

	/**
	 * Unmarshall XML
	 * @param xmlData the XML as string
	 * @return the {@link CurrentSong} unmarshalled
	 */
	private CurrentSong unmarshalllData(String xmlData) {
		try {
			oxmCurrentSong.setSchema(new ClassPathResource("xsd/CurrentSong.xsd"));
			CurrentSong returnXML = (CurrentSong) oxmCurrentSong.unmarshal(new StreamSource(new StringReader(xmlData)));
			return returnXML;
		} catch (XmlMappingException e) {
			LOGGER.warn("Error while unmarshalling normal data", e);
			return null;
		}
	}
	/**
	 * Query server and return XML as string
	 * @return the string
	 */
	private String getXMLAsString() {
		//credentials
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = prepareCredentials();

		//make query
		RestTemplate template = new RestTemplate(simpleClientHttpRequestFactory);
		String result = template.getForObject(properties.getUrl(), String.class);
		LOGGER.debug("Gathered XML : " + result);
		return result;
	}

	/**
	 * Prepare credentials for authentication
	 * @return the {@link SimpleClientHttpRequestFactory} for authentication
	 */ 
	private SimpleClientHttpRequestFactory prepareCredentials() {
		//prepare credentials
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory() {
			@Override
			protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
				super.prepareConnection(connection, httpMethod);

				//Basic Authentication for Police API
				String authorisation = properties.getUsername() + ":" + properties.getPassword();
				byte[] encodedAuthorisation = Base64.encodeBase64(authorisation.getBytes());
				connection.setRequestProperty("Authorization", "Basic " + new String(encodedAuthorisation));
			}
		};
		return simpleClientHttpRequestFactory;
	}

}
