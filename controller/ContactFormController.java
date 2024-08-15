package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.society.model.contactForm;
import com.society.service.ContactService;

import java.util.List;


@RestController
@RequestMapping("/users/contacts")
@CrossOrigin(origins = "http://localhost:4200")

public class ContactFormController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/save")
    public ResponseEntity<contactForm> saveContact(@RequestBody contactForm contactForm) {
        
            contactService.saveContact(contactForm);
            return ResponseEntity.status(HttpStatus.OK).body(contactForm);
        
    }

}
