package io.nology.postcodeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), NotFoundException.getStatusCode());
	}
    
    @ExceptionHandler(ServiceValidationException.class)
    public ResponseEntity<String> handleServiceValidationException(ServiceValidationException ex) {
    	// the ObjectMapper is used to serialize the ValidationErrors object (ex.getErrors()) into a JSON string. 
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
    		String json = objectMapper.writeValueAsString(ex.getErrors());
    		return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    	} catch (JsonProcessingException e) {
    		e.printStackTrace();
    		return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    	}
    }

}
