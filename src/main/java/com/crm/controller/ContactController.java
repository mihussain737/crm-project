package com.crm.controller;

import com.crm.dto.ContactDto;
import com.crm.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/contacts")
@AllArgsConstructor
public class ContactController {

    private ContactService contactService;


    //http://localhost:8080/api/contacts?leadId
    @PostMapping("/{leadId}")
    public ResponseEntity<ContactDto> createContact(@PathVariable String leadId){
        ContactDto contactDto = contactService.createContact(leadId);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);
    }
}
