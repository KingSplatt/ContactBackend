package com.example.contatcs_project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "contatcsplatt")
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable=false)
    private String nombre;

    @Column(name="apellido", nullable=false)
    private String apellido;

    @Column(name="email", nullable=false) 
    private String email;
 
    @Column(name="phone_number", nullable=false)
    private String phone_number;

    @OneToMany (mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phones> phones = new ArrayList<>();

}
