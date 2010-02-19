package org.todomap.geocoder;

/**
 * Checked exception class thrown when a backend system fails.
 * 
 * @author kocka
 *
 */
public class GeoCodeException extends Exception {

	private static final long serialVersionUID = 8127637497285903252L;

	public GeoCodeException() {
		super();
	}

	public GeoCodeException(final String message) {
		super(message);
	}

	public GeoCodeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GeoCodeException(final Throwable cause) {
		super(cause);
	}

}
