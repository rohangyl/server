package com.claims.cms.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.claims.cms.entity.Admin;
import com.claims.cms.entity.HealthClaim;
import com.claims.cms.entity.User;
import com.claims.cms.request.AdminRequest;
import com.claims.cms.request.CreateUserRequest;
import com.claims.cms.request.UserLoginRequest;
import com.claims.cms.service.AdminService;
import com.claims.cms.service.AuthenticationException;
import com.claims.cms.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins="http://localhost:3002")
@RequestMapping("/api/admins")
@Api(value = "Admin Controller", tags = "Admin Management")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;
  

    @GetMapping
    @ApiOperation(value = "Get all admins", notes = "Retrieve a list of all admins.")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    
    @ApiOperation(value = "Get admin by ID", notes = "Retrieve an admin by ID.")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create admin", notes = "Create a new admin.")
    public ResponseEntity<Admin> createAdmin(@RequestBody CreateUserRequest ar) {
        Admin createdAdmin = adminService.createAdmin(ar.getUsername(), ar.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update admin", notes = "Update an existing admin by ID.")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin updatedAdmin = adminService.updateAdmin(id, admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete admin", notes = "Delete an admin by ID.")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping("/login")
//    @ApiOperation(value = "Login admin", notes = "Login an admin.")
//    public ResponseEntity<String> loginAdmin(@RequestBody UserLoginRequest adminLoginRequest) {
//    	
//        try {
//            String token = adminService.loginAdmin(adminLoginRequest.getUsername(), adminLoginRequest.getPassword());
//            return ResponseEntity.ok(token);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during admin login");
//        }
    @PostMapping("/login")
    @ApiOperation(value = "Login admin", notes = "Login an admin.")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody UserLoginRequest adminLoginRequest) {
        try {
            String token = adminService.loginAdmin(adminLoginRequest.getUsername(), adminLoginRequest.getPassword());
            
            // Retrieve admin ID based on the provided username
            Long adminId = adminService.getAdminIdByUsername(adminLoginRequest.getUsername());

            // Create a response map with necessary information
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("adminId", adminId);
            response.put("status", HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid admin credentials"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error during admin login"));
        }
    }

    // CRUD operations for User managed by Admin

    @GetMapping("/users/{adminId}")
    public ResponseEntity<List<User>> getUsersByAdmin(@PathVariable Long adminId) {
        List<User> users = userService.getUsersByAdmin(adminId);
        return ResponseEntity.ok(users);
    }
//
//    @GetMapping("/{adminId}/users/{userId}")
//    public ResponseEntity<User> getUserByAdmin(@PathVariable Long adminId, @PathVariable Long userId) {
//        User user = userService.getUserByAdmin(adminId, userId);
//        return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/{adminId}/users")
//    public ResponseEntity<User> createUserByAdmin(@PathVariable Long adminId, @RequestBody User user) {
//        User createdUser = userService.createUserByAdmin(adminId, user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
//
    @PutMapping("/users/{adminId}/{userId}")
    public ResponseEntity<User> updateUserByAdmin(@PathVariable Long adminId, @PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUserByAdmin(adminId, userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{adminId}/delete/{userId}")
    public ResponseEntity<Void> deleteUserByAdmin(@PathVariable Long adminId, @PathVariable Long userId) {
        userService.deleteUserByAdmin(adminId, userId);
        return ResponseEntity.noContent().build();
    }
}



