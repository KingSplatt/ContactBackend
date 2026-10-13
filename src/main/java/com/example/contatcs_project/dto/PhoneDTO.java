package com.example.contatcs_project.dto;

import com.example.contatcs_project.entity.Contact;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data 
@AllArgsConstructor 
public class PhoneDTO {
    private final Long id;
    private final String phone_number;
    private final Long id_type;
}
