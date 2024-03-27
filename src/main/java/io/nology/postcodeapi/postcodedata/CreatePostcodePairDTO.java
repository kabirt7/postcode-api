package io.nology.postcodeapi.postcodedata;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreatePostcodePairDTO {
	
	@Positive(message = "Postcode number must be a positive number")
	private Integer postcodeNumber;
	
	@NotBlank
	private String suburbName;

	public Integer getPostcodeNumber() {
		return postcodeNumber;
	}

	public String getSuburbName() {
		return suburbName;
	}
	
    // only used for testing purposes
	public CreatePostcodePairDTO(Integer postcodeNumber, String suburbName) {
	    this.postcodeNumber = postcodeNumber;
	    this.suburbName = suburbName;
	}
	
}
