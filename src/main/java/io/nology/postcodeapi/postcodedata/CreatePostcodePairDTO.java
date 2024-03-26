package io.nology.postcodeapi.postcodedata;

import jakarta.validation.constraints.NotBlank;

public class CreatePostcodePairDTO {
	
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
