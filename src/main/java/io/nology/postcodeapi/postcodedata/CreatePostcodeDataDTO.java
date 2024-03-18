package io.nology.postcodeapi.postcodedata;

import jakarta.validation.constraints.NotBlank;

public class CreatePostcodeDataDTO {
	
	@NotBlank
	private Long postcode;
	
	@NotBlank
	private String suburb;

	public Long getPostcode() {
		return postcode;
	}

	public String getSuburb() {
		return suburb;
	}
	
}
