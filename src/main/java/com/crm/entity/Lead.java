package com.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="leads")
public class Lead {

    @Id
    private String id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="title")
    private String title;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="mobile",nullable = false,unique = true)
    private Long mobile;

    @Column(name="address")
    private String address;

    @Column(name="zipCode")
    private int zipCode;

    @Column(name = "designation")
    private String designation;

    @Column(name="company")
    private String company;

    @Column(name="note")
    private String note;
}
