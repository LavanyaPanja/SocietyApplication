package com.society.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.society.model.User;
import com.society.repository.UserRepository;
import com.society.util.JwtUtil;
@Service
public class AuthService {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;
    
   private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

//    public String authenticateAndGenerateToken(String email, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect email or password", e);
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//        return jwtUtil.generateToken(email);
//    }
//    public String login(String email, String password) {
//     
//        User user = userRepository.findByEmail(email);
//                 .orElseThrow(() -> new UsernameNotFoundException("user not found"));
//        if (user.getPassword().equals(password)) {
//            String token = jwtUtil.generateToken(user.getEmail());
//            logger.info("Token generated for patient with email: {}", email);
//            return token;
//        } else {
//            logger.warn("Invalid credentials for patient with email: {}", email);
//            throw new BadCredentialsException("Invalid credentials");
//        }
//    }
   public String loginUser(String email, String password) {
       logger.info("Attempting to log in doctor with email: {}", email);
       User user = userRepository.findByEmail(email)
                           .orElseThrow(() -> new UsernameNotFoundException("Doctor not found"));
       
       if (user.getPassword().equals(password)) {
           String token = jwtUtil.generateToken(user.getEmail());
           logger.info("Token generated for user with email: {}", email);
           return token;
       } else {
           logger.warn("Invalid credentials for user with email: {}", email);
           throw new BadCredentialsException("Invalid credentials");
       }
   }
}
