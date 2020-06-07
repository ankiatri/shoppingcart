package com.ethoca.elimininator.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class User implements Serializable {

    private static final long serialVersionUID = 1722389045480487161L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "Username cannot be null")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password cannot be null")
    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private String email;

}
