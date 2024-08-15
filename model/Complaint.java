package com.society.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Complaint {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String houseNumber;
	    private String email;
	    private String title;
	    private String description;
	    @ManyToOne
	    
	    @JsonIgnore
	    private User user;

	    public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		// Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getHouseNumber() {
	        return houseNumber;
	    }

	    public void setHouseNumber(String houseNumber) {
	        this.houseNumber = houseNumber;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

		public Complaint(Long id, String houseNumber, String email, String title, String description) {
			super();
			this.id = id;
			this.houseNumber = houseNumber;
			this.email = email;
			this.title = title;
			this.description = description;
		}

		public Complaint() {
			
		}

	
}
