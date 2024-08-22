package com.example.contactManager.service;

import com.example.contactManager.model.Contact;
import com.example.contactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact getContactById(Integer id) {
        return contactRepository.findById(id).orElse(null);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Integer id, String name, String email) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        contact.setName(name);
        contact.setEmail(email);
        return contactRepository.save(contact);
    }

    public boolean deleteContact(Integer id) {
        contactRepository.deleteById(id);
        return true;
    }
}
