package com.society.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.model.Defaulter;

@Repository
public interface DefaulterRepository extends JpaRepository<Defaulter, Long> {
}
