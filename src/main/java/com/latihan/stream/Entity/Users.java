package com.latihan.stream.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_users;
    private String email;
    private String name;
    private String password;
    private String username;
    @ManyToOne
    @JoinColumn(name="id_roles", nullable=false)
    private Roles roles;
    @OneToMany(mappedBy = "users")
    private Set<Transaction> transactions;
    @OneToMany(mappedBy = "users")
    private Set<Activity> activities;
}
