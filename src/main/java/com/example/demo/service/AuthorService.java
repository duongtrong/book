package com.example.demo.service;



import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Chapter;

import java.util.List;

public abstract class AuthorService implements MainService<Author> {
    public abstract List<Book> getBooksById(int id );

    public abstract List<Chapter> getChapterById(int id);
}