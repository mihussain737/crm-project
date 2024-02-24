package com.crm.service.impl;

import com.crm.dto.EmailDto;
import com.crm.entity.Email;
import com.crm.entity.Lead;
import com.crm.exception.ContactNotExistException;
import com.crm.exception.LeadNotExistException;
import com.crm.repository.ContactRepository;
import com.crm.repository.EmailRepository;
import com.crm.repository.LeadRepository;
import com.crm.service.EmailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private EmailRepository emailRepository;
    private LeadRepository leadRepository;
    private JavaMailSender javaMailSender;
    private ModelMapper modelMapper;
    private ContactRepository contactRepository;
    @Override
    public EmailDto sendEmail(EmailDto emailDto) {

        Lead lead = leadRepository.findByEmail(emailDto.getTo()).orElseThrow(
                () -> new LeadNotExistException("Email id not registered:-" + emailDto.getTo())
        );

        contactRepository.findByEmail(emailDto.getTo()).orElseThrow(
                ()-> new ContactNotExistException("Contact not exist:-"+ emailDto.getTo())
        );
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDto.getTo());
        message.setSubject(emailDto.getSub());
        message.setText(emailDto.getText());
        javaMailSender.send(message);

        emailDto.setEId(UUID.randomUUID().toString());
        Email email = modelMapper.map(emailDto, Email.class);
        Email sentEmail = emailRepository.save(email);
        return modelMapper.map(sentEmail,EmailDto.class);


    }
}
