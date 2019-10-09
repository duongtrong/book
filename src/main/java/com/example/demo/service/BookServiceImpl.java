package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Chapter;
import com.example.demo.model.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ChapterRepository;
import com.example.demo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BookServiceImpl extends BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Page<Book> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book add(Book o) {
        if (o.getAuthor().getId() != 0) {
            Author author = checkIfIdIsPresentandReturnAuthor(o.getAuthor().getId());
            o.setAuthor(author);
            author.addBook(o);
        }

        if (o.getPublisher().getId() != 0) {
            Publisher publisher = checkIfIdIsPresentandReturnPublisher(o.getPublisher().getId());
            o.setPublisher(publisher);
            publisher.addBook(o);
        }

        o.setId(Calendar.AUGUST);
        return bookRepository.save(o);
    }

    @Override
    public Book update(Book o, int id) {
        Book oldBook = checkIfIdIsPresentandReturnBook(id);
        if (o.getTitle() != null) {
            oldBook.setTitle(o.getTitle());
        }

        if (o.getAuthor() != null) {
            Author author;
            if (o.getAuthor().getId() != 0) {
                //get the author by Id
                author = checkIfIdIsPresentandReturnAuthor(o.getAuthor().getId());
                author.addBook(oldBook);
            } else {
                author = o.getAuthor();
                oldBook.setAuthor(author);
            }
        }

        if (o.getPublisher() != null) {
            Publisher publisher;
            if (o.getPublisher().getId() != 0) {
                publisher = checkIfIdIsPresentandReturnPublisher(o.getPublisher().getId());
                publisher.addBook(oldBook);
            } else {
                publisher = o.getPublisher();
                oldBook.setPublisher(publisher);
            }
        }

        if (o.getPrice() != 0) {
            oldBook.setPrice(o.getPrice());
        }

        return bookRepository.save(oldBook);
    }

    @Override
    public Book getById(int id) {
        return checkIfIdIsPresentandReturnBook(id);
    }

    @Override
    public Book deleteById(int id) {
        Book book = checkIfIdIsPresentandReturnBook(id);
        bookRepository.deleteById(id);
        return book;
    }

    @Override
    public List<Chapter> getChapterById(int id) {
        return checkIfIdIsPresentandReturnBook(id).getChapterList();
    }

    private Book checkIfIdIsPresentandReturnBook( int id) {
        if (!bookRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( " Book id=" + id + " not found" );
        else
            return bookRepository.findById(id).get();
    }

    private Author checkIfIdIsPresentandReturnAuthor(int id) {
        if ( !authorRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( " Author id = " + id + " not found" );
        else
            return authorRepository.findById(id).get();
    }

    private Publisher checkIfIdIsPresentandReturnPublisher(int id) {
        if (!publisherRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" Publisher id = " + id + " not found");
        else
            return publisherRepository.findById(id).get();
    }
}