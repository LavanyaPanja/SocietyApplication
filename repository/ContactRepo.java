package com.society.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.model.contactForm;
@Repository
public interface ContactRepository extends JpaRepository<contactForm, Long> {


}
