package com.claims.cms.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HealthClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_of_claim")
    private LocalDate dateOfClaim;
    private String description;
    private Double amount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "policy_holder_id")
    private PolicyHolder policyHolder;

    @OneToOne(mappedBy = "healthClaim", cascade = CascadeType.ALL)
    private PolicyResponse policyResponse;
     
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus = ClaimStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    private Admin approvedBy;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Admin admin;
    
   
    @Transient
    private boolean expired;
    
    public void checkIfExpired() {
        this.expired = LocalDate.now().minusDays(30).isAfter(dateOfClaim);
    }
    
    public void approveClaim(Admin admin) {
        this.claimStatus = ClaimStatus.APPROVED;
        this.approvedBy = admin;
    }

    public void rejectClaim() {
        this.claimStatus = ClaimStatus.REJECTED;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfClaim() {
		return dateOfClaim;
	}

	public void setDateOfClaim(LocalDate dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}

	public PolicyResponse getPolicyResponse() {
		return policyResponse;
	}

	public void setPolicyResponse(PolicyResponse policyResponse) {
		this.policyResponse = policyResponse;
	}

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	public Admin getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Admin approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public HealthClaim(Long id, LocalDate dateOfClaim, String description, Double amount, String status,
			PolicyHolder policyHolder, PolicyResponse policyResponse, 
			ClaimStatus claimStatus, Admin approvedBy,
			Admin admin, boolean expired, User user) {
		super();
		this.id = id;
		this.dateOfClaim = dateOfClaim;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.policyHolder = policyHolder;
		this.policyResponse = policyResponse;
		this.claimStatus = claimStatus;
		this.approvedBy = approvedBy;
	    this.user = user;
		this.admin = admin;
		this.expired = expired;
	}

	public HealthClaim() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	
    
    
    
}

