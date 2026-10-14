package com.example.contatcs_project.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.contatcs_project.dto.ContactDTO;
import com.example.contatcs_project.dto.PhoneDTO;
import com.example.contatcs_project.entity.Contact;
import com.example.contatcs_project.entity.Phone;
import com.example.contatcs_project.entity.TypePhone;
import com.example.contatcs_project.exception.DuplicateEmailException;
import com.example.contatcs_project.exception.ResourceNotFoundException;
import com.example.contatcs_project.repository.ContactRepository;
import com.example.contatcs_project.repository.PhoneRepository;
import com.example.contatcs_project.repository.TypePhoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final PhoneRepository phoneRepository;
    private final TypePhoneRepository typePhoneRepository;

    public List<ContactDTO> getAllContactsActive() {
        return contactRepository.findByStatus(1).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findByIdAndStatus(id, 1).orElseThrow(() -> new ResourceNotFoundException("Contact with id " + id + " not found"));
        return convertToDTO(contact);
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        if (contactRepository.existsByEmail(contactDTO.getEmail())) {
            throw new DuplicateEmailException("Email " + contactDTO.getEmail() + " is already registered");
        }

        Contact contact = new Contact();
        contact.setNombre(contactDTO.getNombre());
        contact.setApellido(contactDTO.getApellido());
        contact.setEmail(contactDTO.getEmail());

        if (contactDTO.getPhones() != null && !contactDTO.getPhones().isEmpty()) {
            Set<Phone> phones = processPhones(contactDTO.getPhones());
            contact.setPhones(phones);
        }

        contact = contactRepository.save(contact);
        return convertToDTO(contact);
    }

    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findByIdAndStatus(id, 1).orElseThrow(() -> new ResourceNotFoundException("Contact with id " + id + " not found"));

        if (!contact.getEmail().equals(contactDTO.getEmail()) && contactRepository.existsByEmailAndIdNot(contactDTO.getEmail(), id)) {
            throw new DuplicateEmailException("Email " + contactDTO.getEmail() + " is already registered");
        }

        contact.setNombre(contactDTO.getNombre());
        contact.setApellido(contactDTO.getApellido());
        contact.setEmail(contactDTO.getEmail());

        if (contactDTO.getPhones() != null) {
            contact.getPhones().clear();
            if (!contactDTO.getPhones().isEmpty()) {
                Set<Phone> phones = processPhones(contactDTO.getPhones());
                contact.setPhones(phones);
            }
        }

        contact = contactRepository.save(contact);
        return convertToDTO(contact);
    }

    public void deleteContact(Long id) {
        Contact contact = contactRepository.findByIdAndStatus(id, 1).orElseThrow(() -> new ResourceNotFoundException("Contact with id " + id + " not found"));
        contact.setStatus(0);
        contactRepository.save(contact);
    }

    private Set<Phone> processPhones(List<PhoneDTO> phoneDTOs) {
        Set<Phone> phones = new HashSet<>();
        for (PhoneDTO phoneDTO : phoneDTOs) {
            Phone phone;
            if (phoneDTO.getId() != null) {
                phone = phoneRepository.findById(phoneDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Phone with id " + phoneDTO.getId() + " not found"));
            } else {
                phone = new Phone();
                phone.setPhoneNumber(phoneDTO.getPhoneNumber());
                TypePhone type = typePhoneRepository.findById(phoneDTO.getTypeId()).orElseThrow(() -> new ResourceNotFoundException("Phone type with id " + phoneDTO.getTypeId() + " not found"));
                phone.setType(type);
                phone = phoneRepository.save(phone);
            }
            phones.add(phone);
        }
        return phones;
    }

    private ContactDTO convertToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setNombre(contact.getNombre());
        dto.setApellido(contact.getApellido());
        dto.setEmail(contact.getEmail());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setStatus(contact.getStatus());

        if (contact.getPhones() != null && !contact.getPhones().isEmpty()) {
            List<PhoneDTO> phoneDTOs = contact.getPhones().stream().map(this::convertPhoneToDTO).collect(Collectors.toList());
            dto.setPhones(phoneDTOs);
        }

        return dto;
    }

    private PhoneDTO convertPhoneToDTO(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phone.getId());
        phoneDTO.setPhoneNumber(phone.getPhoneNumber());
        if (phone.getType() != null) {
            phoneDTO.setTypeId(phone.getType().getId());
            phoneDTO.setTypeName(phone.getType().getTipo());
        }
        return phoneDTO;
    }
}
