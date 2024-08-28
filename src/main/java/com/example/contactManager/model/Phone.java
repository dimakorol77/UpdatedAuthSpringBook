package com.example.contactManager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String telephone;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonBackReference // Это аннотация предотвращает бесконечную рекурсию
    private Contact contact;

}
