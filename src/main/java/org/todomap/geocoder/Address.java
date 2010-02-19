package org.todomap.geocoder;

import javax.persistence.Embeddable;

/**
 * Address bean.
 * 
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

	/**
	 * Country.
	 * 
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * State, or county in not state-federation countries.
	 * 
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * The name of the town.
	 * 
	 * @return
	 */
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
