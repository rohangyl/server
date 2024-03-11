package com.claims.cms.service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claims.cms.entity.Admin;
import com.claims.cms.entity.User;
import com.claims.cms.repo.AdminRepository;
import com.claims.cms.repo.UserRepository;
import com.claims.cms.request.CreateUserRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Autowired
    private AdminRepository adminRepository;
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
   
      public List<User> getUsersByAdmin(Long adminId) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NoSuchElementException("Admin not found"));

            // You can implement additional logic here, such as checking admin permissions

            return userRepository.findAll();
        }

        public User getUserByAdmin(Long adminId, Long userId) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NoSuchElementException("Admin not found"));

            // You can implement additional logic here, such as checking admin permissions

            return userRepository.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
        }

        public User createUserByAdmin(Long adminId, User user) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NoSuchElementException("Admin not found"));

            // You can implement additional logic here, such as checking admin permissions

            // Implement validation logic if needed
            return userRepository.save(user);
        }

        public User updateUserByAdmin(Long adminId, Long userId, User updatedUser) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NoSuchElementException("Admin not found"));

            // You can implement additional logic here, such as checking admin permissions

            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            // Update properties as needed
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());

            return userRepository.save(existingUser);
        }
        public void deleteUserByAdmin(Long adminId, Long userId) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NoSuchElementException("Admin not found"));


            User userToDelete = userRepository.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            // Implement additional logic to check if the admin has the right to delete users

            userRepository.delete(userToDelete);
        }

    public void createUser(User user) throws DuplicateUsernameException {
        validateUserData(user);
        checkUniqueUsername(user.getUsername());
        userRepository.save(user);
    }
    private void checkUniqueUsername(String username) throws DuplicateUsernameException {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("Username '" + username + "' is already taken");
        }
    }

        private void validateUserData(User user) {
			// TODO Auto-generated method stub
        	  if (user.getUsername() == null || user.equals("")) {
                  throw new IllegalArgumentException("Username cannot be null or empty");
              }

              if (user.getPassword() == null || user.equals("")) {
                  throw new IllegalArgumentException("Password cannot be null or empty");
              }
		}
//
//        private boolean authenticateUser(String username, String password) {
//            // Retrieve user from the database based on the username
//            User user = userRepository.findByUsername(username);
//
//            // Check if the user exists and the password matches
//            return user != null && password.equals(user.getPassword());        }
//        public String loginUser(String username, String password) {
//            // Implement your authentication logic here and return a token
//            // This is a placeholder, replace it with your actual authentication mechanism
//            if (authenticateUser(username, password)) {
//                // Generate and return a token
//                return "Login Succesfully";
//            } else {
//                throw new AuthenticationException("Invalid credentials");
//            }
//        }
		 public String loginUser(String username, String password) {
		        
		        User user = authenticateuser(username, password);
                System.out.println(jwtSecret+"vridhi"+ user);
		        if (user != null) {
		            System.out.println("Gautam");
		            return generateJwtToken(user);
		        } else {
		        	System.out.println("gotiya");
		            return null;
		        }
		    }
	private User authenticateuser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).orElse(null);
}
	//	   @SuppressWarnings("deprecation")
		private String generateJwtToken(User user) {
			   Date expiration = new Date(System.currentTimeMillis() + jwtExpiration);
			   System.out.println("naina"+ expiration);

			   return Jwts.builder()
		                .setSubject(user.getUsername())
		                .setExpiration(expiration)
		                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
		                .compact();
		        
		    }

		public Long getUserIdByUsername(String username) {
			 User user = userRepository.findByUsername(username)
			            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
			        return user.getId();
		}
		

}
   

