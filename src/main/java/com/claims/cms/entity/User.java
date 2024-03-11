package com.claims.cms.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity

@Table(name="userdemo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String username;
	    private String password;

	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    @JsonManagedReference
	    @JsonBackReference
	    private List<HealthClaim> healthClaims = new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public List<HealthClaim> getHealthClaims() {
			return healthClaims;
		}

		public void setHealthClaims(List<HealthClaim> healthClaims) {
			this.healthClaims = healthClaims;
		}

		public User(Long id, String username, String password , List<HealthClaim> healthClaims) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.healthClaims = healthClaims;
		}

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		

	 
	

}
