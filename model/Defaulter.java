package com.society.model;

import jakarta.persistence.*;



@Entity
@Table(name = "defaulter")
public class Defaulter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String familyName;
    private double amountDue;
    private String dueDate;
    private double fine;
	public Defaulter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Defaulter(Long id, String familyName, double amountDue, String dueDate, double fine) {
		super();
		this.id = id;
		this.familyName = familyName;
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.fine = fine;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
    
}
