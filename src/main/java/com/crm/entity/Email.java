package com.crm.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="emails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Id
    private String eId;

    @Column(name="to_email")//to_email because to will give error
    private String to;

    @Column(name="subject")
    private String sub;

    @Column(name="text")
    private String text;
}
