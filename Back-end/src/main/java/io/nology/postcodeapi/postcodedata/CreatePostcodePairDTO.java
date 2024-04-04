package io.nology.postcodeapi.postcodedata;

import java.util.Set;

import jakarta.validation.constraints.Positive;

public class CreatePostcodePairDTO {
    
    @Positive(message = "Postcode number must be a positive number")
    private Integer postcodeNumber;
    
    private Set<SuburbEntity> suburbs;

    public Integer getPostcodeNumber() {
        return postcodeNumber;
    }

    public Set<SuburbEntity> getSuburbs() {
        return suburbs;
    }
    

    public CreatePostcodePairDTO() {
    }

    // Constructor for testing purposes
    public CreatePostcodePairDTO(Integer postcodeNumber, Set<SuburbEntity> suburbs) {
        this.postcodeNumber = postcodeNumber;
        this.suburbs = suburbs;
    }
}


//public String getSuburbsAsString() {
//if (suburbs == null || suburbs.length == 0) {
//  return ""; // Return an empty string if suburbs array is null or empty
//}
//
//StringBuilder suburbNames = new StringBuilder();
//for (int i = 0; i < suburbs.length; i++) {
//  if (suburbs[i] != null) {
//      suburbNames.append(suburbs[i].getSuburbName());
//      if (i < suburbs.length - 1) {
//          suburbNames.append(", "); // Add comma and space if it's not the last suburb
//      }
//  }
//}
//return suburbNames.toString();
//}

// Default constructor required for deserialization by Jackson