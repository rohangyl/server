package com.claims.cms.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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

import com.claims.cms.entity.HealthClaim;
import com.claims.cms.entity.User;
import com.claims.cms.repo.UserRepository;
import com.claims.cms.request.CreateUserRequest;
import com.claims.cms.request.UserLoginRequest;
import com.claims.cms.service.AdminService;
import com.claims.cms.service.AuthenticationException;
import com.claims.cms.service.DuplicateUsernameException;
import com.claims.cms.service.HealthClaimService;
import com.claims.cms.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="http://localhost:3002")
@RequestMapping("/users")
@Api(value = "User Management", tags = "User Operations")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userrepo;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @GetMapping("/rohan")
    public void test(){
    	System.out.println("Working");
    }
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private HealthClaimService healthClaimService;
    
    @ApiOperation(value = "Login User", notes = "Logs in a user and returns an authentication token")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
    	 try {
             String token = userService.loginUser(userLoginRequest.getUsername(), userLoginRequest.getPassword());
             Long userId = userService.getUserIdByUsername(userLoginRequest.getUsername());

             // Create a response map with necessary information
             Map<String, Object> response = new HashMap<>();
             response.put("token", token);
             response.put("userId", userId);
             response.put("status", HttpStatus.OK.value());
             return ResponseEntity.ok(response);
         } catch (AuthenticationException e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid user credentials"));
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error during user login"));
         }
     }
    @ApiOperation(value = "Create User", notes = "Creates a new user")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
//        User createdUser = userService.createUser(createUserRequest.getUsername(), createUserRequest.getPassword());
//        
//        return ResponseEntity.ok("UserCreated"+createdUser);
    	try {
            // Convert UserRequest to User entity
            User newUser = new User();
            newUser.setUsername(createUserRequest.getUsername());
            newUser.setPassword(createUserRequest.getPassword());

            // Call UserService to create user
            userService.createUser(newUser);

            return new ResponseEntity<>("User created successfully And Userid is:- "+newUser.getId(), HttpStatus.CREATED);
        } catch (DuplicateUsernameException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ApiOperation(value = "Get Health Claims by User", notes = "Retrieves health claims associated with a user")
    @GetMapping("/{userId}/claims")
    public ResponseEntity<List<HealthClaim>> getHealthClaimsByUser(@PathVariable Long userId) {
        List<HealthClaim> healthClaims = healthClaimService.getHealthClaimsByUser(userId);
        return ResponseEntity.ok(healthClaims);
    }

    @ApiOperation(value = "List of users", notes = "List of User")
    @GetMapping("/users/{adminId}")
    public ResponseEntity<List<User>> getUsersByAdmin(@PathVariable("adminId")  Long adminId) {
        List<User> users = userService.getUsersByAdmin(adminId);
        return ResponseEntity.ok(users);
    }
    @ApiOperation(value = "Get user by id", notes = "Retrieves health claims associated with a user")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByAdmin(@PathVariable Long adminId, @PathVariable Long userId) {
        User user = userService.getUserByAdmin(adminId, userId);
        return ResponseEntity.ok(user);
    }
    @ApiOperation(value = "create by admin", notes = "Retrieves health claims associated with a user")
    @PostMapping("/create/users")
    public ResponseEntity<User> createUserByAdmin(@PathVariable Long adminId, @RequestBody User user) {
        User createdUser = userService.createUserByAdmin(adminId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @ApiOperation(value = "uPDATE USER", notes = "Retrieves health claims associated with a user")
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserByAdmin(@PathVariable Long adminId, @PathVariable Long userId, @RequestBody User updatedUser) {
        User updatedUserResult = userService.updateUserByAdmin(adminId, userId, updatedUser);
        return ResponseEntity.ok(updatedUserResult);
    }
    @ApiOperation(value = "GDELETE USER", notes = "Retrieves health claims associated with a user")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserByAdmin(@PathVariable Long adminId, @PathVariable Long userId) {
    	userService.deleteUserByAdmin(adminId, userId);
        return ResponseEntity.noContent().build();
    }

   
}
