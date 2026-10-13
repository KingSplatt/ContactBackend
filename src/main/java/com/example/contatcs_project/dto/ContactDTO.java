package com.example.contatcs_project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long id;

    @NotNull (message = "Contact nombre is required")
    private String nombre;

    @NotNull (message = "Contact nombre is required") 
    private String apellido;

    @NotNull (message = "Contact nombre is required")
    private String email;

    @NotNull (message = "Contact nombre is required")
    private String phone_number;

    private Long id_phone;

    
}
