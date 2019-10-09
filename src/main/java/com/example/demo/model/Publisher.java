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
@Table(name = "publisher")
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    //@JsonManagedReference("publisher")
    @JsonIgnore
    private List<Book> bookList= new ArrayList<>();

    public void addBook(Book book) {
        this.bookList.add(book);
    }

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    //@JsonManagedReference("publisher")
    @JsonIgnore
    private List<Chapter> chapterList= new ArrayList<>();

    public void addChapter(Chapter chapter) {
        this.chapterList.add(chapter);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;

    private int  status;

    public Publisher() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = 1;
    }
}