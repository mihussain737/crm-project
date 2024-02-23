package com.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadDto {

    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private Long mobile;
    private String address;
    private int zipCode;
    private String designation;
    private String company;
    private String note;
}
