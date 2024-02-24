package com.crm.controller;

import com.crm.dto.LeadDto;
import com.crm.dto.LeadResponse;
import com.crm.entity.Lead;
import com.crm.service.LeadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{lid}")
    public ResponseEntity<String> deleteByLid(@PathVariable String lid){
        leadService.deleteLeadByLid(lid);
        return new ResponseEntity<>("Lead is deleted with id:-"+lid,HttpStatus.OK);
    }

    @GetMapping()
    public LeadResponse getAllLeads(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        LeadResponse allLeads = leadService.getAllLeads(pageNo,pageSize,sortBy,sortDir);
        return allLeads;
    }
}
