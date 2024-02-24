package com.crm.service.impl;

import com.crm.dto.LeadDto;
import com.crm.dto.LeadResponse;
import com.crm.entity.Lead;
import com.crm.exception.LeadAlreadyExistsException;
import com.crm.exception.LeadNotExistException;
import com.crm.repository.LeadRepository;
import com.crm.service.LeadService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeadServiceImpl implements LeadService {

    private LeadRepository leadRepository;
    private ModelMapper modelMapper;

    @Override
    public LeadDto saveLead(LeadDto leadDto) {
        boolean email = leadRepository.existsByEmail(leadDto.getEmail());
        if(email){
            throw new LeadAlreadyExistsException("Email already exists:-"+leadDto.getEmail());
        }
        boolean mobile=leadRepository.existsByMobile(leadDto.getMobile());
                if(mobile){
            throw new LeadAlreadyExistsException("Mobile already exists:-"+leadDto.getMobile());
        }
        leadDto.setLid(UUID.randomUUID().toString());
        Lead lead = modelMapper.map(leadDto, Lead.class);
        Lead saveLead = leadRepository.save(lead);
       return modelMapper.map(saveLead, LeadDto.class);
    }
    public void deleteLeadByLid(String lid) {
        leadRepository.findById(lid).orElseThrow(
                ()-> new LeadNotExistException("Lead not exist with Id:-"+lid)
        );
        leadRepository.deleteById(lid);

    }

    @Override
    public LeadResponse getAllLeads(int pageNo, int pageSize, String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Lead> leads = leadRepository.findAll(pageable);
        List<Lead> all = leads.getContent();
        List<LeadDto> collect = all.stream().map(lead -> modelMapper.map(lead, LeadDto.class)).collect(Collectors.toList());

        LeadResponse leadResponse=new LeadResponse();
        leadResponse.setContent(collect);
        leadResponse.setPageNo(leads.getNumber());
        leadResponse.setTotalPages(leads.getTotalPages());
        leadResponse.setTotalElements((int)leads.getTotalElements());
        leadResponse.setPageSize(leads.getSize());
        leadResponse.setLast(leads.isLast());
        return leadResponse;
    }

    @Override
    public List<Lead> getLeadsExcelReports() {
        List<Lead> leads = leadRepository.findAll();
        return leads;
    }
}
