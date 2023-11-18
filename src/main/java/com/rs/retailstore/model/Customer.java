package com.rs.retailstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tự động tăng --> không cần thao tác chèn data id
    private int id;
    private String username;
    private String password;
    private String role;
}
