package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chapter")
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "forename")
    private String forename;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @JoinColumn(name = "book_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Book books;

    //@JsonBackReference("author")
    @JoinColumn(name = "author_id", nullable = false)
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Author author;

    //@JsonBackReference("publisher")
    @JoinColumn(name = "publisher_id", nullable = false)
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;

    private int status;

    public Chapter() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = 1;
    }
}
