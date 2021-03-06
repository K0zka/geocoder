package org.todomap.geocoder.google;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.todomap.geocoder.Address;
import org.todomap.geocoder.GeoCodeException;
import org.todomap.geocoder.GeoCoder;
import org.todomap.geocoder.LatLng;
import org.todomap.geocoder.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Google geocoder implementation.
 * 
 * @author kocka
 * 
 */
public class GoogleGeocoder implements GeoCoder {

	private abstract class EventHandler extends DefaultHandler {
		private final Stack<String> elementNameStack = new Stack<String>();
		private String status = null;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public final void characters(final char[] ch, final int start,
				final int length) throws SAXException {
			final String characterData = new String(ch, start, length).trim();
			logger.debug(elementName + " -> " + characterData);
			if (characterData.length() > 0) {

				if ("code".equals(elementName)) {
					status = characterData;
				}

				characters(characterData);
			}
		}

		abstract void characters(String characterData);

		/**
		 * {@inheritDoc}
		 */
		@Override
		public final void endElement(final String uri, final String localName,
				final String qName) throws SAXException {
			if (!elementNameStack.isEmpty()) {
				elementNameStack.pop();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public final void startElement(final String uri,
				final String localName, final String qName,
				final Attributes attributes) throws SAXException {
			if (elementName != null) {
				elementNameStack.push(elementName);
			}
			elementName = qName;
		}
	}

	private final static Logger logger = Logger.getLogger(GoogleGeocoder.class);

	final static SAXParserFactory parserFactory = SAXParserFactory
			.newInstance();

	private String apiKey = null;

	private String elementName = null;

	/**
	 * {@inheritDoc}
	 */
	public LatLng geocode(final Address address) throws GeoCodeException {
		final LatLng latLng = new LatLng();

		final EventHandler handler = new EventHandler() {
			@Override
			public void characters(final String characterData) {
				if ("coordinates".equals(elementName)) {
					final String[] split = characterData.split(",");
					latLng.setLat(Double.parseDouble(split[0]));
					latLng.setLng(Double.parseDouble(split[1]));
				}
			}
		};

		parse(handler, "http://maps.google.com/maps/geo?q="
				+ address.getCountry() + "+" + address.getCountry()
				+ "&output=xml&oe=utf8&sensor=false&key=" + apiKey);

		return latLng;
	}

	public String getApiKey() {
		return apiKey;
	}

	private void parse(final EventHandler handler, final String reqURL)
			throws GeoCodeException {
		try {
			final InputStream stream = new URL(reqURL).openStream();

			final SAXParser parser = parserFactory.newSAXParser();
			parser.parse(stream, handler);
			stream.close();
		} catch (final IOException e) {
			throw new GeoCodeException(e);
		} catch (final SAXException e) {
			throw new GeoCodeException(e);
		} catch (final ParserConfigurationException e) {
			throw new GeoCodeException(e);
		}
		if (!"200".equals(handler.status)) {
			throw new GeoCodeException("Geocoder service returned status "
					+ handler.status);
		}
	}

	public Address revert(final LatLng loc) throws GeoCodeException {
		final Address addr = new Address();

		final EventHandler handler = new EventHandler() {

			@Override
			public void characters(final String characterData) {
				if ("CountryNameCode".equals(elementName)) {
					addr.setCountry(characterData);
				} else if ("LocalityName".equals(elementName)) {
					addr.setTown(characterData);
				} else if ("ThoroughfareName".equals(elementName)) {
					addr.setAddress(characterData);
				} else if ("AdministrativeAreaName".equals(elementName)) {
					addr.setState(characterData);
				} else if("PostalCodeNumber".equals(elementName)) {
					addr.setPostalCode(characterData);
				}
			}

		};
		parse(handler,
				"http://maps.google.com/maps/geo?output=xml&oe=utf-8&ll="
						+ loc.getLat() + "%2C" + loc.getLng() + "&key="
						+ apiKey);

		return addr;
	}

	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}

}
