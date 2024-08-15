package com.society.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
	@Column(name = "name")
private String name;
	@Column(nullable = false, unique = true)
private String email;
//private Long phoneNumber;
private String password;
private String confirmpassword;
private String role;
@OneToMany(mappedBy = "user" )
private List<Complaint> complaints;
@OneToMany(mappedBy = "user")
private List<Bill> bills;
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(Long id, String name, String email, String password, String confirmpassword, String role,
		List<Complaint> complaints, List<Bill> bills) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
//	this.phoneNumber = phoneNumber;
	this.password = password;
	this.confirmpassword = confirmpassword;
	this.role = role;
	this.complaints = complaints;
	this.bills = bills;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirmpassword() {
	return confirmpassword;
}
public void setConfirmpassword(String confirmpassword) {
	this.confirmpassword = confirmpassword;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public List<Complaint> getComplaints() {
	return complaints;
}
public void setComplaints(List<Complaint> complaints) {
	this.complaints = complaints;
}
public List<Bill> getBills() {
	return bills;
}
public void setBills(List<Bill> bills) {
	this.bills = bills;
}

}
