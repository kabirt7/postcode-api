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
	
}
