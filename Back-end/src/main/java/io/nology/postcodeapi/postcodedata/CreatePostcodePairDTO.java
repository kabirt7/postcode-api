package io.nology.postcodeapi.postcodedata;

import java.util.Set;

import jakarta.validation.constraints.Positive;

public class CreatePostcodePairDTO {
    
    @Positive(message = "Postcode number must be a positive number")
    private Integer postcodeNumber;
    
    @ValidSuburbName
    private Set<SuburbEntity> suburbs;

    public Integer getPostcodeNumber() {
        return postcodeNumber;
    }

    public Set<SuburbEntity> getSuburbs() {
        return suburbs;
    }
    
    

    @Override
	public String toString() {
		return "[suburbs=" + suburbs + "]";
	}

	public CreatePostcodePairDTO() {
    }

    // Constructor for testing purposes
    public CreatePostcodePairDTO(Integer postcodeNumber, Set<SuburbEntity> suburbs) {
        this.postcodeNumber = postcodeNumber;
        this.suburbs = suburbs;
    }
}

