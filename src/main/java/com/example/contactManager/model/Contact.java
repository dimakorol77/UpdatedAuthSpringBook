package com.example.contactManager.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


import java.util.UUID;
@Data
@Builder
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;


}
//Без @Entity Spring (или другой фреймворк) не поймёт, что этот класс должен быть представлен в базе данных в виде таблицы.
// Это значит, что данные, которые ты собираешь в объекте этого класса, не будут автоматически сохраняться в БД.
//Убрав @Entity, ты просто сделаешь этот класс обычным Java-классом, который больше не будет связан с БД.