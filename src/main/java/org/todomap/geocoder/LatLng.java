package org.todomap.geocoder;

import javax.persistence.Embeddable;

@Embeddable
public class LatLng {
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
