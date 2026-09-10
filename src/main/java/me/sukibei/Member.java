package me.sukibei;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity

public class Member {
    @Id
    @Column(name="id",nullable = false)
    private  Long id;
    @Column(name="name",nullable = false)
    private String name;
}
