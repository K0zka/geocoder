package org.todomap.geocoder;

public interface GeoCoder {
	LatLng geocode(Address address);
	Address revert(LatLng loc);
}
