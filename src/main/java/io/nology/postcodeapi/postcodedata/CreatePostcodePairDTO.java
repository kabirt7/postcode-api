package io.nology.postcodeapi.postcodedata;

import jakarta.validation.constraints.NotBlank;

public class CreatePostcodePairDTO {
	
	private Long postcodeNumber;
	
	@NotBlank
	private String suburbName;

	public Long getPostcodeNumber() {
		return postcodeNumber;
	}

	public String getSuburbName() {
		return suburbName;
	}
	
}
