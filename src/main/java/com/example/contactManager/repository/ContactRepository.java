package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    Contact save(Contact contact);

    Optional<Contact> findById(Integer id);

    List<Contact> findAll();

    void deleteById(Integer id);
}
