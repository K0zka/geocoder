package org.todomap.geocoder;

import javax.persistence.Embeddable;

/**
 * Address bean.
 * @author kocka
 *
 */
@Embeddable
public class Address {
	private String address;
	private String country;
	private String state;
	private String town;

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getTown() {
		return town;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public void setTown(final String town) {
		this.town = town;
	}
}
