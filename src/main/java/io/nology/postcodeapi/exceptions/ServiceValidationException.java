package io.nology.postcodeapi.exceptions;

public class ServiceValidationException extends Exception {
	
	// this is needed for version control of serialised objects which this function
	// is due to its implementation of the Exception class
	private static final long serialVersionUID = 1L;
	
	private ValidationErrors errors;
	
	public ServiceValidationException(ValidationErrors errors) {
	// this instantiates the superclass Exception ensuring that necessary initialisation occurs
    super();
    // this class does not directly instantiate anything, it passes the errors as 
	// a reference to the ValidationErrors class which will then be instantiated
	// by the constructor class
		this.errors = errors;
	}
	
	public ValidationErrors getErrors() {
		return errors;
	}

}
