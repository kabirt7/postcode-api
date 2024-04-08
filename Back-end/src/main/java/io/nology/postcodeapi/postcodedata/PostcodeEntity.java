package io.nology.postcodeapi.postcodedata;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "postcodeapi_data")
public class PostcodeEntity {
    @Id
    @Column
    private Integer postcodeNumber;
    
    @OneToMany(mappedBy = "postcode")
    private Set<SuburbEntity> suburbs = new HashSet<>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;
    
    @PrePersist
    public void onCreate() {
        Date timestamp = new Date();
        createdAt = timestamp;
        updatedAt = timestamp;
    }
	
	@Override
	public String toString() {
		return "[suburbs=" + suburbs + "]";
	}

	@PreUpdate
	public void onUpdate() {
		updatedAt = new Date();
	}

	public Integer getPostcodeNumber() {
		return postcodeNumber;
	}

	public Set<SuburbEntity> getSuburbs() {
		return suburbs;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setPostcodeNumber(Integer postcodeNumber) {
		this.postcodeNumber = postcodeNumber;
	}

	public void setSuburbs(Set<SuburbEntity> suburbs) {
		this.suburbs = suburbs;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


}


