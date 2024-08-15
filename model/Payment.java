package com.society.model;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Payment {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;



 private LocalDate date;
 private Long cardNumber;
 private String expiryDate;
 private Integer cvv;
 private Double amount;
 private String description;
 //private Boolean status;
 @OneToOne
 @JoinColumn(name = "bill_id")
 private Bill bill;
 
public Payment() {
	super();
	// TODO Auto-generated constructor stub
}
public Payment(Long id, Bill bill, LocalDate date, Long cardNumber, String expiryDate, Integer cvv, Double amount,
		String description) {
	super();
	this.id = id;
	this.bill = bill;
	this.date = date;
	this.cardNumber = cardNumber;
	this.expiryDate = expiryDate;
	this.cvv = cvv;
	this.amount = amount;
	this.description = description;
	//this.status = status;
}

public Payment(Long id, Double amount, String description) {
	super();
	this.id = id;
	this.amount = amount;
	this.description = description;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Bill getBill() {
	return bill;
}
public void setBill(Bill bill) {
	this.bill = bill;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public Long getCardNumber() {
	return cardNumber;
}
public void setCardNumber(Long cardNumber) {
	this.cardNumber = cardNumber;
}
public String getExpiryDate() {
	return expiryDate;
}
public void setExpiryDate(String expiryDate) {
	this.expiryDate = expiryDate;
}
public Integer getCvv() {
	return cvv;
}
public void setCvv(Integer cvv) {
	this.cvv = cvv;
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
//public Boolean getStatus() {
//	return status;
//}
//public void setStatus(Boolean status) {
//	this.status = status;
//}


 
}

