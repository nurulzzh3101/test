package com.latihan.test.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "idxUser",
                columnList = "name, email, phone, is_deleted, nik")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_phone", columnNames = {"phone"}),
                @UniqueConstraint(name = "unique_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_nik", columnNames = {"nik"})
        }
)
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false)
    public String id;

    @Column(name = "name", length = 50, nullable = false)
    public String name;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    public String email;

    @Column(name = "phone", length = 15, unique = true, nullable = false)
    public String phone;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "nik", length = 16)
    public String nik;

    @Column(name = "is_deleted", nullable = false)
    public Boolean isDeleted;
}
