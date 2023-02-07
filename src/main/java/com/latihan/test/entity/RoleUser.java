package com.latihan.test.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "role", indexes = {
        @Index(name = "idxRole",
                columnList = "name, is_deleted")
})
public class RoleUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false)
    public String id;

    @Column(name = "name", length = 50, nullable = false)
    public String name;

    @Column(name = "is_deleted", nullable = false)
    public Boolean isDeleted;
}
