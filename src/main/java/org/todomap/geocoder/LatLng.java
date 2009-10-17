package org.todomap.geocoder;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LatLng implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029128489555505090L;
	public LatLng() {
		super();
	}
	public LatLng(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	double lat;
	double lng;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
}
