package com.example.contatcs_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contatcs_project.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    //Contact findByIdID(Long id);
}
