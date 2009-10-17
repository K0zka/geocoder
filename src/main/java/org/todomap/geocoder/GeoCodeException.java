package org.todomap.geocoder;

public class GeoCodeException extends Exception {

	private static final long serialVersionUID = 8127637497285903252L;

	public GeoCodeException() {
		super();
	}

	public GeoCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeoCodeException(String message) {
		super(message);
	}

	public GeoCodeException(Throwable cause) {
		super(cause);
	}

}
