package com.ardas.entity;

import javax.persistence.*;

@Entity
@Table(name = "Contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
