package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @JsonIgnore
    @Column(name = "chapter")
    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chapter> chapterList = new ArrayList<>();

    public void addChapter(Chapter chapter) {
        this.chapterList.add(chapter);
    }

    @NotNull
    @Column(name = "thumbnail")
    private String thumbnail;

    //@JsonBackReference("author")
    @JoinColumn(name = "author_id", nullable = false)
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Author author;

    //@JsonBackReference("publisher")
    @JoinColumn(name = "publisher_id", nullable = false)
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @NotNull
    @Column(name = "price")
    private double price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;

    private int  status;

    public Book() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = 1;
    }
}