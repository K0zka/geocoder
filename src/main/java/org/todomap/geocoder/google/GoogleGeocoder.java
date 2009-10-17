package org.todomap.geocoder.google;

import java.io.InputStream;
import java.net.URL;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.todomap.geocoder.Address;
import org.todomap.geocoder.GeoCoder;
import org.todomap.geocoder.LatLng;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GoogleGeocoder implements GeoCoder {

	String apiKey = null;

	private final static Logger logger = Logger.getLogger(GoogleGeocoder.class);

	public LatLng geocode(final Address address) {
		return null;
	}

	public Address revert(final LatLng loc) {
		final Address addr = new Address();

		try {
			final URL reqURL = new URL(
					"http://maps.google.com/maps/geo?output=xml&oe=utf-8&ll="
							+ loc.getLat() + "%2C" + loc.getLng() + "&key="
							+ apiKey);
			final InputStream stream = reqURL.openStream();

			final SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			final DefaultHandler hb = new DefaultHandler() {

				final Stack<String> elementNameStack = new Stack<String>();
				String elementName = null;
				
				@Override
				public void characters(char[] ch, int start, int length)
						throws SAXException {
					final String characterData = new String(ch, start, length);
					if(characterData.trim().length() > 0) {
						if("CountryNameCode".equals(elementName)) {
							addr.setCountry(characterData);
						} else if("LocalityName".equals(elementName)) {
							addr.setTown(characterData);
						} else if("ThoroughfareName".equals(elementName)) {
							addr.setAddress(characterData);
						} else if("AdministrativeAreaName".equals(elementName)) {
							addr.setState(characterData);
						}
					}
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					elementNameStack.pop();
				}

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if(elementName != null) {
						elementNameStack.push(elementName);
					}
					elementName = qName;
				}
			};
			parser.parse(stream, hb);
		} catch (Exception e) {
			logger.error("Exception on resolving the locale", e);
			
		}

		return addr;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
