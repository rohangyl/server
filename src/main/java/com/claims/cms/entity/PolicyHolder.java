package com.claims.cms.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PolicyHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactDetails;

    @OneToMany(mappedBy = "policyHolder", cascade = CascadeType.ALL)
    private List<HealthClaim> healthClaims = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<HealthClaim> getHealthClaims() {
		return healthClaims;
	}

	public void setHealthClaims(List<HealthClaim> healthClaims) {
		this.healthClaims = healthClaims;
	}

	public PolicyHolder(Long id, String name, String address, String contactDetails, List<HealthClaim> healthClaims) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contactDetails = contactDetails;
		this.healthClaims = healthClaims;
	}

	public PolicyHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
    

    
}


