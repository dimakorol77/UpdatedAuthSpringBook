package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseContactRepository extends JpaRepository<Contact, Integer>, ContactRepository {
}
