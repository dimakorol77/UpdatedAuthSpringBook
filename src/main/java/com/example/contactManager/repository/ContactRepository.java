package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository {
    Contact create(Contact contact);

    Contact getContactById(Integer id);

    List<Contact> getAllContacts();

    Contact update(Contact contact);

    boolean delete(Integer id);
}



//JpaRepository — это интерфейс, который является частью Spring Data JPA.
//// Он предоставляет набор стандартных методов для работы с базой данных (CRUD операции, пагинация и сортировка).
// Вот основные функции, которые предоставляет JpaRepository:
//
//CRUD операции:
//
//save(S entity): Сохраняет или обновляет сущность.
//findById(ID id): Ищет сущность по её ID.
//findAll(): Возвращает список всех сущностей.
//deleteById(ID id): Удаляет сущность по её ID.
//deleteAll(): Удаляет все сущности.
//Пагинация и сортировка:
//
//findAll(Pageable pageable): Возвращает страницу сущностей.
//findAll(Sort sort): Возвращает отсортированный список сущностей.
//Пользовательские запросы:
//
//Можно определять собственные методы, используя ключевые слова Spring Data JPA, такие как findBy, countBy, deleteBy, и т.д. Это позволяет автоматически генерировать запросы на основе имени метода.
//