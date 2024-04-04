package io.nology.postcodeapi.postcodedata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "suburb")
public class SuburbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String suburbName;
    
    @ManyToOne
    @JsonIgnore // Ignore postcode to prevent circular serialization
    private PostcodeEntity postcode;
    
    // Constructors, getters, setters, and lifecycle callbacks...
    
    public SuburbEntity() {
    	
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSuburbName() {
        return suburbName;
    }
    
    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

    public PostcodeEntity getPostcode() {
        return postcode;
    }

    public void setPostcode(PostcodeEntity postcode) {
        this.postcode = postcode;
    }
}

