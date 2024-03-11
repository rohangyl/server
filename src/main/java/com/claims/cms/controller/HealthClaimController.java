package com.claims.cms.controller;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claims.cms.entity.Admin;
import com.claims.cms.entity.HealthClaim;
import com.claims.cms.repo.AdminRepository;
import com.claims.cms.service.AdminService;
import com.claims.cms.service.HealthClaimService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins="http://localhost:3002")
@RestController
@RequestMapping("/api")
@Api(value = "Health Claim Controller", tags = "Health Claim Management")
public class HealthClaimController {

    @Autowired
    private HealthClaimService healthClaimService;

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private AdminRepository adminRepository;

    
 
    @PostMapping("/health-claims/{claimId}/approve")
    @ApiOperation(value = "Approve Health Claim", notes = "Approve a health claim by admin.")

    public ResponseEntity<HealthClaim> approveHealthClaim(
            @PathVariable Long claimId,
            @RequestParam String adminUsername,
            @RequestParam String adminPassword) throws AuthenticationException {

        // Perform admin authentication
        Admin admin = adminRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new AuthenticationException("Invalid admin credentials"));

        if (admin.getPassword().equals(adminPassword)) {
            HealthClaim approvedClaim = healthClaimService.approveClaim(claimId, adminUsername, adminPassword);
            return ResponseEntity.ok(approvedClaim);
        } else {
            throw new AuthenticationException("Invalid admin credentials");
        }
    }
  

   @GetMapping
   @ApiOperation(value = "Get all health claims", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<List<HealthClaim>> getAllHealthClaims() {
        List<HealthClaim> healthClaims = healthClaimService.getAllHealthClaims();
        return ResponseEntity.ok(healthClaims);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all health claims by id", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<HealthClaim> getHealthClaimById(@PathVariable Long id) {
        HealthClaim healthClaim = healthClaimService.getHealthClaimById(id);
        return ResponseEntity.ok(healthClaim);
    }

    @PostMapping("/create")
    @ApiOperation(value = "create health claims", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<HealthClaim> createHealthClaim(@RequestBody HealthClaim healthClaim) {
        HealthClaim createdHealthClaim = healthClaimService.createHealthClaim(healthClaim);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHealthClaim);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update all health claims", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<HealthClaim> updateHealthClaim(@PathVariable Long id, @RequestBody HealthClaim healthClaim) {
        HealthClaim updatedHealthClaim = healthClaimService.updateHealthClaim(id, healthClaim);
        return ResponseEntity.ok(updatedHealthClaim);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete all health claims", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<Void> deleteHealthClaim(@PathVariable Long id) {
        healthClaimService.deleteHealthClaim(id);
        return ResponseEntity.noContent().build();
    }

    // Additional Endpoints

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get all health claims by user", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<List<HealthClaim>> getHealthClaimsByUser(@PathVariable Long userId) {
        List<HealthClaim> healthClaims = healthClaimService.getHealthClaimsByUser(userId);
        return ResponseEntity.ok(healthClaims);
    }

    @GetMapping("/admin/{adminId}")
    @ApiOperation(value = "Get all health claims by admin", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<List<HealthClaim>> getHealthClaimsByAdmin(@PathVariable Long adminId) {
        List<HealthClaim> healthClaims = healthClaimService.getHealthClaimsByAdmin(adminId);
        return ResponseEntity.ok(healthClaims);
    }

    @PostMapping("/admin/{adminId}")
    @ApiOperation(value = "createall health claims by admin", notes = "Retrieve a list of all health claims.")

    public ResponseEntity<HealthClaim> createHealthClaimByAdmin(
            @PathVariable Long adminId, @RequestBody HealthClaim healthClaim) {
        HealthClaim createdHealthClaim = healthClaimService.createHealthClaimByAdmin(adminId, healthClaim);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHealthClaim);
    }

//    @PostMapping("/user/{userId}")
//    public ResponseEntity<HealthClaim> createHealthClaimByUser(
//            @PathVariable Long userId, @RequestBody HealthClaim healthClaim) {
//        HealthClaim createdHealthClaim = healthClaimService.createHealthClaimByUser(userId, healthClaim);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdHealthClaim);
//    }
}


