package com.example.contatcs_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.contatcs_project.dto.ContactDTO;
import com.example.contatcs_project.entity.Contact;
import com.example.contatcs_project.repository.ContactRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ContactService {
    private final ContactRepository contactRepository;

    public List<ContactDTO> getAllContacts(){
        List<ContactDTO> contacts = contactRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return contacts;
    }

    public ContactDTO getContacById(Long id){
        Contact contact = contactRepository.findById(id).orElseThrow(()-> new RuntimeException("contact doesnt found"));
        return convertToDTO(contact);
    }

    public ContactDTO createContact(ContactDTO contactDTO){
        Contact contact = convertToEntity(contactDTO);
        contactRepository.save(contact);
        return convertToDTO(contact);
    }

    public ContactDTO updateContact(Long id,ContactDTO contactDTO){
        Contact contact = contactRepository.findById(id).orElseThrow(()-> new RuntimeException("contact doesnt found"));
        //Contact contact = contactRepository.findByIdID(id);
        contact.setNombre(contactDTO.getNombre());
        contact.setApellido(contactDTO.getApellido());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone_number(contactDTO.getPhone_number());
        contact = contactRepository.save(contact);
        return convertToDTO(contact);
    }

    public void deleteContact(Long id){
        Contact contact = contactRepository.findById(id).orElseThrow(()-> new RuntimeException("contact doesnt found"));
        //contact.setStatus(0)
        contactRepository.delete(contact);
    }

    

    

    public Contact convertToEntity (ContactDTO contactDTO){
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setNombre(contactDTO.getNombre());
        contact.setApellido(contactDTO.getApellido());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone_number(contactDTO.getPhone_number());
        return contact;
    }

    public ContactDTO convertToDTO(Contact contact){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setNombre(contact.getNombre());
        contactDTO.setApellido(contact.getApellido());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhone_number(contact.getPhone_number());
        return contactDTO;
    }

}
