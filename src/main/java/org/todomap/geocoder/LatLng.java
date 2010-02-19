package org.todomap.geocoder;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Latitude and longitude.
 * 
 * @author kocka
 */
@Embeddable
public class LatLng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029128489555505090L;
	private double lat;
	private double lng;

	public LatLng() {
		super();
	}

	public LatLng(final double lat, final double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLat(final double lat) {
		this.lat = lat;
	}

	public void setLng(final double lng) {
		this.lng = lng;
	}
}
