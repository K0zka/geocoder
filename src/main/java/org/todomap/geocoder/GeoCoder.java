package org.todomap.geocoder;

public interface GeoCoder {
	LatLng geocode(Address address) throws GeoCodeException;
	Address revert(LatLng loc) throws GeoCodeException;
}
