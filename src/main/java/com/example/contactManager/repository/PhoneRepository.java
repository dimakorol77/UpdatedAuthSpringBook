package com.example.contactManager.repository;


import com.example.contactManager.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    List<Phone> findAllByContactId(Integer contactId);
}
