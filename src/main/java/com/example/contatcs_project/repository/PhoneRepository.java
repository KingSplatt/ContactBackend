package com.example.contatcs_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contatcs_project.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
