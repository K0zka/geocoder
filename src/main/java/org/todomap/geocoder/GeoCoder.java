package org.todomap.geocoder;

/**
 * Geocoder interface.
 * 
 * @author kocka
 * 
 */
public interface GeoCoder {
	/**
	 * Find the coordinates of the address.
	 * 
	 * @param address
	 *            address
	 * @return coordinates
	 * @throws GeoCodeException
	 *             if a backend operation goes wrong
	 */
	LatLng geocode(Address address) throws GeoCodeException;

	/**
	 * Reverse geocode coordinates into a postal address.
	 * 
	 * @param loc
	 * @return
	 * @throws GeoCodeException
	 *             if a backend operation goes wrong
	 */
	Address revert(LatLng loc) throws GeoCodeException;
}
