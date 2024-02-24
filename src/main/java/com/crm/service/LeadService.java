package com.crm.service;

import com.crm.dto.LeadDto;
import com.crm.dto.LeadResponse;

import java.util.List;

public interface LeadService {

    public LeadDto saveLead(LeadDto leadDto);

    public void deleteLeadByLid(String lid);
    LeadResponse getAllLeads(int pageNo, int pageSize, String sortBy, String sortDir);
}
