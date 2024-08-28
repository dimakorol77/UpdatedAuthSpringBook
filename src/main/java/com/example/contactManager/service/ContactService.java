package com.example.contactManager.service;

import com.example.contactManager.model.Contact;
import com.example.contactManager.model.Phone;
import com.example.contactManager.repository.ContactRepository;
import com.example.contactManager.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public Contact getContactById(Integer id, Integer ownerId) {
        return contactRepository.findByIdAndOwnerId(id, ownerId).orElse(null);
    }

    public List<Contact> getAllContacts(Integer ownerId) {
        return contactRepository.findAllByOwnerId(ownerId);
    }

    public Contact addContact(Contact contact, Integer ownerId) {
        contact.setOwnerId(ownerId);
        return contactRepository.save(contact);
    }

    public Contact updateContact(Integer id, String name, String email, Integer ownerId) {
        Contact contact = contactRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(() -> new RuntimeException("Contact not found or not owned by the user"));
        contact.setName(name);
        contact.setEmail(email);
        return contactRepository.save(contact);
    }

    public boolean deleteContact(Integer id, Integer ownerId) {
        Contact contact = contactRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(() -> new RuntimeException("Contact not found or not owned by the user"));
        contactRepository.delete(contact);
        return true;
    }

    public List<Phone> getPhonesByContactId(Integer contactId, Integer ownerId) {
        Contact contact = contactRepository.findByIdAndOwnerId(contactId, ownerId)
                .orElseThrow(() -> new RuntimeException("Contact not found or not owned by the user"));
        return phoneRepository.findAllByContactId(contactId);
    }

    public Phone addPhoneToContact(Integer contactId, Phone phone, Integer ownerId) {
        Contact contact = contactRepository.findByIdAndOwnerId(contactId, ownerId)
                .orElseThrow(() -> new RuntimeException("Contact not found or not owned by the user"));
        phone.setContact(contact);
        return phoneRepository.save(phone);
    }

    public void deletePhone(Integer phoneId, Integer ownerId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
        Contact contact = phone.getContact();
        if (!contact.getOwnerId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized action");
        }
        phoneRepository.delete(phone);
    }
}
