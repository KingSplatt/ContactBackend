package com.example.contatcs_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "phones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="phone_number", nullable=false)
    private String phone_number;

    @Column(name="id_type", nullable=false)
    private Long id_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phone", nullable = false)
    Contact contact;

}
