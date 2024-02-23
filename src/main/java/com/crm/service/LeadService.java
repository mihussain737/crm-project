package com.crm.service;

import com.crm.dto.LeadDto;

import java.util.List;

public interface LeadService {

    public LeadDto saveLead(LeadDto leadDto);

    public void deleteLead(String lid);
    List<LeadDto> getAllLeads();
}
