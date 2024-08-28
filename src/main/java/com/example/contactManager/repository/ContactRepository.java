package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Optional<Contact> findByIdAndOwnerId(Integer id, Integer ownerId);

    List<Contact> findAllByOwnerId(Integer ownerId);
}
