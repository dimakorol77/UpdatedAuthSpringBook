package com.example.contactManager.controller;

import com.example.contactManager.model.Contact;
import com.example.contactManager.model.Phone;
import com.example.contactManager.service.ContactService;
import com.example.contactManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestHeader("Authorization") String token, @RequestBody Contact contact) {
        Integer ownerId = getUserIdFromToken(token);
        Contact savedContact = contactService.addContact(contact, ownerId);
        return ResponseEntity.ok(savedContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody Contact contact) {
        Integer ownerId = getUserIdFromToken(token);
        Contact updatedContact = contactService.updateContact(id, contact.getName(), contact.getEmail(), ownerId);
        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        Integer ownerId = getUserIdFromToken(token);
        boolean deleted = contactService.deleteContact(id, ownerId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/phones")
    public ResponseEntity<List<Phone>> getPhonesByContactId(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        Integer ownerId = getUserIdFromToken(token);
        List<Phone> phones = contactService.getPhonesByContactId(id, ownerId);
        return ResponseEntity.ok(phones);
    }

    @PostMapping("/{id}/phones")
    public ResponseEntity<Phone> addPhoneToContact(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody Phone phone) {
        Integer ownerId = getUserIdFromToken(token);
        Phone savedPhone = contactService.addPhoneToContact(id, phone, ownerId);
        return ResponseEntity.ok(savedPhone);
    }

    @DeleteMapping("/phones/{phoneId}")
    public ResponseEntity<Void> deletePhone(@RequestHeader("Authorization") String token, @PathVariable Integer phoneId) {
        Integer ownerId = getUserIdFromToken(token);
        contactService.deletePhone(phoneId, ownerId);
        return ResponseEntity.noContent().build();
    }

    private Integer getUserIdFromToken(String token) {
        return userService.findByToken(token)
                .map(user -> Math.toIntExact(user.getId()))
                .orElseThrow(() -> new RuntimeException("Invalid token"));
    }
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(@RequestHeader("Authorization") String token) {
        Integer ownerId = getUserIdFromToken(token);
        List<Contact> contacts = contactService.getAllContacts(ownerId);
        return ResponseEntity.ok(contacts);
    }

}
