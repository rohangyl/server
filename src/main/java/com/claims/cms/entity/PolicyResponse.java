package com.claims.cms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class PolicyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responseDetails;
    private String status;

    @OneToOne
    @JoinColumn(name = "health_claim_id")
    private HealthClaim healthClaim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResponseDetails() {
		return responseDetails;
	}

	public void setResponseDetails(String responseDetails) {
		this.responseDetails = responseDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HealthClaim getHealthClaim() {
		return healthClaim;
	}

	public void setHealthClaim(HealthClaim healthClaim) {
		this.healthClaim = healthClaim;
	}

	public PolicyResponse(Long id, String responseDetails, String status, HealthClaim healthClaim) {
		super();
		this.id = id;
		this.responseDetails = responseDetails;
		this.status = status;
		this.healthClaim = healthClaim;
	}

	public PolicyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}



