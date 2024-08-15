package com.society.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.society.model.User;
import com.society.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
 
	 @Autowired
	    private UserRepository userRepository;
 
	   
 
 
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Optional<User> optionalUser = userRepository.findByEmail(email);
	        
//	        if (optionalUser.isPresent()) {
//	            User user = optionalUser.get();
//	            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
//	            
//	        }
	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();
	            return new org.springframework.security.core.userdetails.User(
	                user.getEmail(), user.getPassword(), getAuthorities(user.getRole()));
	        }
	       
 
	        throw new UsernameNotFoundException("User not found with email: " + email);
	    }


	    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
	        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	    }

		public User registerUser(User register) {
			// TODO Auto-generated method stub
			return userRepository.save(register);
		}




		public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
		}




		public Optional<User> getUserById(Long id) {
			return userRepository.findById(id);
		}




		public List<User> getAllUsers() {
			return userRepository.findAll();
		}
		public boolean checkUserIdExists(Long userId) {
		    return userRepository.existsById(userId);
		}
		 public boolean emailExists(String email) {
		        return userRepository.findByEmail(email).isPresent();
		    }
		 public Optional<User> getUserByEmail(String email) {
		        return userRepository.findByEmail(email);
		    }
}	
