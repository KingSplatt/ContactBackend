package com.example.contatcs_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.contatcs_project.dto.ContactDTO;
import com.example.contatcs_project.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/contacts")
@RequiredArgsConstructor 
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity <List<ContactDTO>> getAllContact(){
        return ResponseEntity.ok(contactService.getAllContacts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity <ContactDTO> getAllContact(@PathVariable Long id){
        return ResponseEntity.ok(contactService.getContacById(id));
    }

    @PostMapping
    public ResponseEntity <ContactDTO> createContact( @Valid @RequestBody ContactDTO contactDTO){
        return ResponseEntity.ok(contactService.createContact(contactDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity <ContactDTO> updateContact(@PathVariable Long id,@Valid @RequestBody ContactDTO contactDTO){
        return ResponseEntity.ok(contactService.updateContact(id,contactDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
    }
}
