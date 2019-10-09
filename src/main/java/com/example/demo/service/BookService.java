package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Chapter;

import java.util.List;

public abstract class BookService implements MainService<Book> {
    public abstract List<Chapter> getChapterById(int id);
}