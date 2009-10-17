package org.todomap.geocoder.google;

import junit.framework.Assert;

import org.junit.Test;
import org.todomap.geocoder.Address;
import org.todomap.geocoder.GeoCodeException;
import org.todomap.geocoder.LatLng;

public class GoogleGeocoderTest {
	@Test
	public void testGeocode() throws GeoCodeException {
		GoogleGeocoder geocoder = createGeoCoder();

		Address address = new Address();
		address.setCountry("Hungary");
		address.setTown("Budapest");
		geocoder.geocode(address);
	}

	@Test
	public void testRevert() throws GeoCodeException {
		GoogleGeocoder geocoder = createGeoCoder();
		
		LatLng loc = new LatLng();
		loc.setLat(47.48135407127781);
		loc.setLng(19.05265885162353);
		Address revert = geocoder.revert(loc);
		
		Assert.assertEquals("Budapest", revert.getTown());
		Assert.assertEquals("HU", revert.getCountry());
	}
	
	private GoogleGeocoder createGeoCoder() {
		GoogleGeocoder geocoder = new GoogleGeocoder();
		geocoder.setApiKey("ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA");
		return geocoder;
	}
}
