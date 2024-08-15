package com.society.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.model.contactForm;
import com.society.repository.ContactRepository;

import java.util.List;


@Service

public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void saveContact(contactForm contactForm) {
        contactRepository.save(contactForm);
    }

    public List<contactForm> getAllContacts(){
        return contactRepository.findAll();
    }
}

