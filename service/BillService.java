package com.society.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.exception.BillNotFoundException;
import com.society.model.Bill;
import com.society.model.User;
import com.society.repository.BillRepository;
import com.society.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

 @Autowired
 private BillRepository billRepository;
 @Autowired
 private UserRepository userRepository;

 public List<Bill> getAllBills() {
     return billRepository.findAll();
 }

 public Bill getBillById(Long id) {
	 
     return billRepository.findById(id).orElse(null);
 }

public Bill saveBill(Bill bill) {
	// TODO Auto-generated method stub
	return billRepository.save(bill);
}

public Bill saveBill(Long userId, Bill bill) {
	
	User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
	bill.setUser(user);
    return billRepository.save(bill);
}

public List<Bill> getPaidBill(Long userId) {
	User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
	List<Bill> allBills=user.getBills();
	List<Bill> paidBills=new ArrayList<Bill>();
	for(Bill b: allBills) {
	if(b.getIsPaid()==true) {
		paidBills.add(b);
		
	}}
	return paidBills;
}
public List<Bill> getUnPaidBill(Long userId) {
	User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
	List<Bill> allBills=user.getBills();
	List<Bill> unpaidBills=new ArrayList<Bill>();
	for(Bill b: allBills) {
	if(!b.getIsPaid()==true) {
		unpaidBills.add(b);
		
	}}
	return unpaidBills;
}
public List<Bill> getBillsByUserId(Long userId) {
    return billRepository.findByUserId(userId);
}
@Transactional
public Bill updateBill(Long id, Bill updatedBill) {
    return billRepository.findById(id)
            .map(bill -> {
                bill.setDescription(updatedBill.getDescription());
                bill.setAmount(updatedBill.getAmount());
                bill.setIsPaid(updatedBill.getIsPaid());
                bill.setDate(updatedBill.getDate());
                // Set other fields as necessary
                bill.setUser(updatedBill.getUser());
                return billRepository.save(bill);
            })
            .orElseThrow(() -> new BillNotFoundException("Bill not found with id " + id));
}
}
