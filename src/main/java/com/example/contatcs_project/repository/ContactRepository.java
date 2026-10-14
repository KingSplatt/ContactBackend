package com.example.contatcs_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.contatcs_project.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByIdAndStatus(Long id, Integer status);
    List<Contact> findByStatus(Integer status);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByEmail(String email);

}
