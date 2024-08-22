package com.example.contactManager.service;

import com.example.contactManager.model.Contact;
import com.example.contactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    // private final List<Contact> contacts = new ArrayList<>(); - не увидела в ТЗ,
    // про гибкость в реализации хранения данных, сделала только для коллекций, чекрез стримы
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public Contact getContactById(Integer id) {
        return contactRepository.getContactById(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.getAllContacts();
    }

    public Contact addContact(Contact contact) {
        return contactRepository.create(contact);
    }


    public Contact updateContact(Integer id, String name, String email) {
        Contact contact = contactRepository.getContactById(id);
        if (contact == null) {
            return null;
        }
        contact.setName(name);
        contact.setEmail(email);
        return contactRepository.update(contact);
    }

    public boolean deleteContact(Integer id) {
        return contactRepository.delete(id);
    }






//    public Contact getContactById(UUID id) {
//        return contacts.stream()
//                .filter(contact -> contact.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Contact not found"));
//    }
//    public List<Contact> getAllContacts() {
//        return new ArrayList<>(contacts); // выозвращаем копию списка контактов
//    }
//    public Contact addContact(Contact contact) {
//        contacts.add(contact);
//        return contact;
//    }
//    public Contact updateContact(UUID id, Contact updatedContact) {
//        return contacts.stream()
//                .filter(contact -> contact.getId().equals(id))
//                .findFirst()
//                .map(contact -> {
//                    contact.setName(updatedContact.getName());
//                    contact.setEmail(updatedContact.getEmail());
//                    return contact;
//                })
//                .orElseThrow(() -> new RuntimeException("Contact not found"));
//    }
//    public void deleteContact(UUID id) {
//        contacts.removeIf(contact -> contact.getId().equals(id));
//    }

    //пример
//    public Contact getContact() {
//        return Contact.builder()
//                .id(UUID.randomUUID())
//                .name("John")
//                .email("john@example.com")
//                .build();
    }



