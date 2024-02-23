package com.crm.controller;

import com.crm.dto.LeadDto;
import com.crm.service.LeadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leads")
@AllArgsConstructor
public class LeadController {

    private LeadService leadService;

    @PostMapping
    public ResponseEntity<LeadDto> saveLead(@RequestBody LeadDto leadDto){
        LeadDto saveLead=leadService.saveLead(leadDto);
        return new ResponseEntity<>(saveLead, HttpStatus.CREATED);
    }
}
