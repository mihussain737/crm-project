package com.crm.service.impl;

import com.crm.dto.ContactDto;
import com.crm.entity.Contact;
import com.crm.entity.Lead;
import com.crm.exception.LeadNotExistException;
import com.crm.repository.ContactRepository;
import com.crm.repository.LeadRepository;
import com.crm.service.ContactService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private LeadRepository leadRepository;
    private ContactRepository contactRepository;
    private ModelMapper modelMapper;
    @Override
    public ContactDto createContact(String lid) {

        Lead lead = leadRepository.findById(lid).orElseThrow(
                () -> new LeadNotExistException("Lead with this id does not exist:-" + lid)
        );
        Contact contact = modelMapper.map(lead, Contact.class);
        contact.setCid(UUID.randomUUID().toString());
        Contact savedContact=contactRepository.save(contact);

        if(savedContact.getCid()!=null){
            leadRepository.deleteById(lead.getLid());
        }

       return modelMapper.map(savedContact,ContactDto.class);

    }

}
