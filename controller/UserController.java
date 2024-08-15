package com.society.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.society.exception.InvalidUserException;
import com.society.exception.UserNotFoundException;
import com.society.model.AuthenticationResponse;
import com.society.model.Bill;
import com.society.model.Complaint;
import com.society.model.User;
import com.society.repository.UserRepository;
import com.society.service.BillService;
import com.society.service.UserService;
import com.society.util.JwtUtil;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	  @Autowired
	    private UserService userService;
	  @Autowired
	    private JwtUtil jwtUtil;
	  @Autowired
	  private UserRepository userRepository;
  @Autowired
  private AuthenticationManager authenticationManager;
//  @Autowired
//  private PasswordEncoder passwordEncoder;

	    @GetMapping
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }

	    @GetMapping("/{id}")
	    public Optional<User> getUserById(@PathVariable Long id) {
	    	Optional<User> user = userService.getUserById(id);
	    	if (user.isEmpty()) {
	            throw new UserNotFoundException("User not found with id: " + id);
	        }
	        return user;
	        
	    }
//	     
//	    
//
//	    @PostMapping
//	    public User saveUser(@RequestBody User user ) {
//	    	 if (user.getName() == null || user.getEmail() == null) {
//	             throw new InvalidUserException("User name and email are required.");
//	         }
//	         return userService.saveUser(user);
//	    }
//	    @PutMapping("/{id}")
//	    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//	        user.setId(id);
//	        return userService.saveUser(user);
//	    }
//
//	    @DeleteMapping("/{id}")
//	    public void deleteUser(@PathVariable Long id) {
//	        userService.deleteUserById(id);
//	    }
//	  

	   

  

    @PostMapping("/register")
    public User registerUser(@RequestBody User register) {
    	register.setPassword(new BCryptPasswordEncoder().encode(register.getPassword()));
    	register.setConfirmpassword(new BCryptPasswordEncoder().encode(register.getConfirmpassword()));
    	   
        return userService.registerUser(register);
    }

    @GetMapping("/id/{id}")
    public Optional<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
    
  
//    @PostMapping("/login")
//    public User login(@RequestBody User user) {
//        // Check if the user exists in the database
//        User existingUser = userService.loginUser(user.getEmail(),user.getPassword());
//        return existingUser;
//    }
    
    
//    
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
//        }
//
//        final UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
   
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        }

      final Optional<User> userDetails = userRepository.findByEmail(user.getEmail());
      
        final String jwt = jwtUtil.generateToken(user.getEmail());
        
        return ResponseEntity.ok(new AuthenticationResponse(jwt,userDetails));
    }
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.emailExists(email));
    }
    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user == null) {
        	throw new UserNotFoundException("User not found with email: " + email);
	        }else {
	    return user;
}}
    }


