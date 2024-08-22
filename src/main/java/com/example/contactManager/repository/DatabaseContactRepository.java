package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseContactRepository extends JpaRepository<Contact, Integer> {
}
//spring.jpa.hibernate.ddl-auto=update
//        spring.jpa.show-sql=true
//// — Hibernate автоматически создаст таблицы, если их нет, и обновит структуру, если есть изменения.
//// — включает логирование SQL-запросов, чтобы видеть, какие запросы выполняются.