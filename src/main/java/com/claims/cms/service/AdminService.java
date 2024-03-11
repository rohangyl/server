package com.claims.cms.service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claims.cms.entity.Admin;
import com.claims.cms.entity.HealthClaim;
import com.claims.cms.repo.AdminRepository;
import com.claims.cms.request.AdminRequest;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.security.Keys;

@Service
public class AdminService {
	@Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Autowired
    private HealthClaimService healthClaimService;

    @Autowired
    private AdminRepository adminRepository;
    

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin not found"));
    }

    public Admin createAdmin(String username, String password) {
    	validateadmin(username,password);
         Admin ar =new Admin();
         ar.setUsername(username);
         ar.setPassword(password);
         
        return adminRepository.save(ar);
    }

    private void validateadmin(String username, String password) {
		// TODO Auto-generated method stub
    	 if (username == null || username.equals("")) {
             throw new IllegalArgumentException("Username cannot be null or empty");
         }

         if (password == null || password.equals("")) {
             throw new IllegalArgumentException("Password cannot be null or empty");
         }
	}

	public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existingAdmin = getAdminById(id);

        
        existingAdmin.setUsername(updatedAdmin.getUsername());
        existingAdmin.setPassword(updatedAdmin.getPassword());

        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public String loginAdmin(String username, String password) {
	        // Implement your authentication logic for admin login
	        // If authentication is successful, generate and return a JWT token

        Admin admin = authenticateAdmin(username, password);
        
        if (admin != null) {
            return generateJwtToken(admin);
        } else {
            throw new AuthenticationException("Invalid admin credentials");
        }
    }

//    public String loginAdmin(String username, String password) {
//        Admin admin = authenticateAdmin(username, password);
//
//        if (admin != null) {
//            return jwtTokenProvider.generateJwtToken(admin);
//        } else {
//            throw new AuthenticationException("Invalid admin credentials");
//        }
//    }
	
	
	@SuppressWarnings("deprecation")
	private String generateJwtToken(Admin admin) {
		Date expiration = new Date(System.currentTimeMillis() + jwtExpiration);
		
		return Jwts.builder()
                .setSubject(admin.getUsername())
                .setExpiration(expiration)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
}	

	private Admin authenticateAdmin(String username, String password) {
		return adminRepository.findByUsernameAndPassword(username, password).orElse(null);
	}

	public Long getAdminIdByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));
        return admin.getId();
    }



}

 



