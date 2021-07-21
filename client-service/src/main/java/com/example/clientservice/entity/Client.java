package com.example.clientservice.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.clientservice.model.Photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_client")
public class Client{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_document", nullable = false)
    @NotEmpty(message = "El tipo de documento no puede ser vacío")
    private String typeDoc;

    @NotEmpty(message = "El número de documento no puede ser vacío")
    @Size( min = 10 , max = 10, message = "El tamaño del número de documento es 10")
    @Column(name = "number_id" , unique = true ,length = 8, nullable = false)
    private String numberID;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="first_name", nullable=false)
    private String firstName;

    @NotEmpty(message = "El apellido no puede ser vacío")
    @Column(name="last_name", nullable=false)
    private String lastName;

    @NotEmpty(message = "la edad no puede ser vacía")
    @Column(name="age", nullable=false)
    private int age;

    @NotEmpty(message = "la ciudad de nacimiento no puede ser vacía")
    @Column(name="city", nullable=false)
    private String city;

    @Transient
    private Photo photo;

    @Column(name="photo_id")
    private String photoId;

    private String state;
}
