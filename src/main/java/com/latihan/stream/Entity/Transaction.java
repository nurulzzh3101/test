package com.latihan.stream.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date date;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Type type;
    @ManyToOne
    @JoinColumn(name="id_users", nullable=false)
    private Users users;
}
