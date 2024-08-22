package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Primary
@Repository
public class InMemoryContactRepository implements ContactRepository{
    private final List<Contact> contacts = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(); //для генерации уникальных идентификаторов в памяти.
    @Override
    public Contact create(Contact contact) {
        contact.setId(counter.incrementAndGet());
        contacts.add(contact);
        return contact;
    }

    @Override
    public Contact getContactById(Integer id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    @Override
    public Contact update(Contact contact) {
        int index = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(contact.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            contacts.set(index, contact);
            return contact;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
//можно использовать аннотацию @Primary для приоритета одного репозитория перед другим.
//
//Если в будущем потребуется переключиться между репозиториями, можно сделать это с
// помощью конфигурационных настроек в application.properties или использовать условные аннотации Spring,