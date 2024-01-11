package com.aleyyu.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;
}
