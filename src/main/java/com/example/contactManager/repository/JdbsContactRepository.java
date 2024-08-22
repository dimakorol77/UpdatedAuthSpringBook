package com.example.contactManager.repository;

import com.example.contactManager.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Primary
@Repository
public class JdbsContactRepository implements ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            // Insert
            String sql = "INSERT INTO contacts (name, email) VALUES (?, ?)";
            jdbcTemplate.update(sql, contact.getName(), contact.getEmail());
        } else {
            // Update
            String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getId());
        }
        return contact;
    }

    @Override
    public Optional<Contact> findById(Integer id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return Optional.of(Contact.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build());
            } else {
                return Optional.empty();
            }
        }, id);
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Contact.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
