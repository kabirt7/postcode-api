package io.nology.postcodeapi.exceptions;

import java.util.ArrayList;
import java.util.HashMap;

public class ValidationErrors {
	
private HashMap<String, ArrayList<String>>errors;
	
	public ValidationErrors() {
	// this is assigned to the instance variable hence its of type HashMap<String, ArrayList<String>>
		this.errors = new HashMap<>();
	}
	
	public void addError(String field, String reason) {
		if(this.errors.containsKey(field)) {
			// errors and this.errors are the same
			errors.get(field).add(reason);
		} else {
			ArrayList<String> newList = new ArrayList<>();
			newList.add(reason);
			errors.put(field, newList);
		}
	}
	
	public boolean isEmpty() {
		return this.errors.isEmpty();
	}
	
	public boolean hasErrors() {
		return !this.isEmpty();
	}
	
	public HashMap<String, ArrayList<String>> getErrors() {
		return errors;
	}

}
