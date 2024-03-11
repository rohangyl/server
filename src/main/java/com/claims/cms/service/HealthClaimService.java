package com.claims.cms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.claims.cms.entity.Admin;
import com.claims.cms.entity.ClaimStatus;
import com.claims.cms.entity.HealthClaim;
import com.claims.cms.entity.User;
import com.claims.cms.repo.AdminRepository;
import com.claims.cms.repo.HealthClaimRepository;
import com.claims.cms.repo.UserRepository;


@Service
public class HealthClaimService {
	@Autowired
    private HealthClaimRepository healthClaimRepository;
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private AdminRepository adminRepository;
	
	//  
	
//	//List of claims and also use in react
//    public List<HealthClaim> getAllHealthClaims() {
//        return healthClaimRepository.findAll();
//    }
	// Check if each claim is expired
    public List<HealthClaim> getAllHealthClaims() {
        List<HealthClaim> healthClaims = healthClaimRepository.findAll();
        healthClaims.forEach(HealthClaim::checkIfExpired); 
        return healthClaims;
    }
    public HealthClaim createHealthClaim(HealthClaim healthClaim) {
        // Validate claim date before saving
        if (isClaimDateValid(healthClaim.getDateOfClaim())) {
            return healthClaimRepository.save(healthClaim);
        } else {
            throw new IllegalArgumentException("Claim date is expired.");
        }
    }

    // Method to check if a claim date is valid
    private boolean isClaimDateValid(LocalDate dateOfClaim) {
        return LocalDate.now().minusDays(30).isBefore(dateOfClaim);
    }
    
    public HealthClaim approveClaim(Long claimId, String adminUsername, String adminPassword) throws AuthenticationException {
        // Perform admin authentication
        Admin admin = adminRepository.findByUsername(adminUsername)
        		.orElseThrow(() -> new AuthenticationException("Invalid admin credentials"));
       
        
        
        
        
        if (admin.getPassword().equals(adminPassword)) {
            HealthClaim healthClaim = healthClaimRepository.findById(claimId)
                    .orElseThrow(() -> new ClaimNotFoundException("Claim not found"));

            // Check if the claim is pending before approving
            if (healthClaim.getClaimStatus() == ClaimStatus.PENDING) {
                healthClaim.approveClaim(admin);
                return healthClaimRepository.save(healthClaim);
            } else {
                throw new IllegalStateException("Claim has already been processed.");
            }
        } else {
            throw new AuthenticationException("Invalid admin credentials");
        }
    }

//    // ... other methods
//
    private boolean isValidCredentials(String username, String password) {
        // Implement your authentication logic here
        // For simplicity, you may use hardcoded credentials, but in a real-world scenario, use a secure authentication mechanism
        return "admin".equals(username) && "password".equals(password);
    }
    public HealthClaim createHealthClaimByAdmin(Long adminId, HealthClaim healthClaim) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("Admin not found"));

        // You can implement additional logic here, such as checking admin permissions

        healthClaim.setAdmin(admin);
        return healthClaimRepository.save(healthClaim);
    }
//    
    public List<HealthClaim> getHealthClaimsByAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new NoSuchElementException("Admin not found"));

        // You can implement additional logic here, such as checking admin permissions

        return healthClaimRepository.findByAdmin(admin);
    }
    
    public HealthClaim getHealthClaimById(Long id) {
        return healthClaimRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("HealthClaim not found"));
    }
    public HealthClaim updateHealthClaim(Long id, HealthClaim updatedHealthClaim) {
        HealthClaim existingHealthClaim = getHealthClaimById(id);

        // Update properties as needed
        existingHealthClaim.setAmount(updatedHealthClaim.getAmount());
        existingHealthClaim.setDateOfClaim(updatedHealthClaim.getDateOfClaim());
        // Update other properties...

        return healthClaimRepository.save(existingHealthClaim);
    }
    public void deleteHealthClaim(Long id) {
        healthClaimRepository.deleteById(id);
    }
    public List<HealthClaim> getHealthClaimsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // You can implement additional logic here, such as checking user permissions

        return healthClaimRepository.findByUser(user);
    }


}


