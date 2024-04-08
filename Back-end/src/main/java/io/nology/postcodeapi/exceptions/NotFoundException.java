package io.nology.postcodeapi.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;
	
	public <T> NotFoundException(Integer id) {
		super(String.format("Could not find Postcode with number %s", id));
	}
	
	public <T> NotFoundException(String id) {
		super(String.format("Could not find Suburb with name %s", id));
	}
	
	public static HttpStatus getStatusCode() {
		return statusCode;
	}

}
