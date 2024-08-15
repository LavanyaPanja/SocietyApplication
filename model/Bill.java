package com.society.model;



import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Bill {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private LocalDate date;
 private Double amount;
 private String description;
 private Boolean isPaid;
 @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
 @JsonIgnore
 private Payment payments;
 @ManyToOne
 @JsonIgnore
 private User user;
public Bill() {
	
}
public Bill(Long id, LocalDate date, Double amount, String description, Boolean isPaid) {
	super();
	this.id = id;
	this.date = date;
	this.amount = amount;
	this.description = description;
	this.isPaid = isPaid;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Boolean getIsPaid() {
	return isPaid;
}
public void setIsPaid(Boolean isPaid) {
	this.isPaid = isPaid;
}
public Payment getPayments() {
	return payments;
}
public void setPayments(Payment payments) {
	this.payments = payments;
}



}
