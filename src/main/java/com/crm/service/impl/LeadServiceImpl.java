package com.crm.service.impl;

import com.crm.dto.LeadDto;
import com.crm.entity.Lead;
import com.crm.repository.LeadRepository;
import com.crm.service.LeadService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LeadServiceImpl implements LeadService {

    private LeadRepository leadRepository;
    private ModelMapper modelMapper;

    @Override
    public LeadDto saveLead(LeadDto leadDto) {
        leadDto.setLid(UUID.randomUUID().toString());
        Lead lead = modelMapper.map(leadDto, Lead.class);
        Lead saveLead = leadRepository.save(lead);
       return modelMapper.map(saveLead, LeadDto.class);
    }

    @Override
    public void deleteLead(String lid) {

    }

    @Override
    public List<LeadDto> getAllLeads() {
        return null;
    }
}
