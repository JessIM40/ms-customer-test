package com.bootcamp.mscustomer.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String lastname;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String dni;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;
}
