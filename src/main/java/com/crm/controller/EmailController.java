package com.crm.controller;

import com.crm.dto.EmailDto;
import com.crm.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private EmailService emailService;


    @PostMapping
    public ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailDto){
        EmailDto sendEmail = emailService.sendEmail(emailDto);
        return new ResponseEntity<>(sendEmail, HttpStatus.OK);
    }
}
