package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Chapter;
import com.example.demo.model.Publisher;

import java.util.List;

public abstract class PublisherService implements MainService<Publisher>{
    public abstract List<Book> getBooksById(int id );

    public abstract List<Chapter> getChapterById(int id);
}
