package com.example.contactManager.controller;

import com.example.contactManager.model.Contact;
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

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Integer id) {
        Contact contact = contactService.getContactById(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestHeader("Authorization") String token, @RequestBody Contact contact) {
        validateToken(token);
        Contact savedContact = contactService.addContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody Contact contact) {
        validateToken(token);
        Contact updatedContact = contactService.updateContact(id, contact.getName(), contact.getEmail());
        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        validateToken(token);
        boolean deleted = contactService.deleteContact(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private void validateToken(String token) {
        if (token == null || !userService.findByToken(token).isPresent()) {
            throw new RuntimeException("Unauthorized access");
        }
    }
}
