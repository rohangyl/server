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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "approvedBy", cascade = CascadeType.ALL)
    private List<HealthClaim> approvedClaims = new ArrayList<>();

	public Long getId() {
		return admin_id;
	}

	public void setId(Long id) {
		this.admin_id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<HealthClaim> getApprovedClaims() {
		return approvedClaims;
	}

	public void setApprovedClaims(List<HealthClaim> approvedClaims) {
		this.approvedClaims = approvedClaims;
	}

	public Admin(Long id, String username, String password , List<HealthClaim> approvedClaims) {
		super();
		this.admin_id = id;
		this.username = username;
		this.password = password;
		this.approvedClaims = approvedClaims;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	

    
}



