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

@Service
public class ChapterServiceImpl extends ChapterService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    ChapterRepository chapterRepository;


    @Override
    public Page<Chapter> getAll(Pageable pageable) {
        return chapterRepository.findAll(pageable);
    }

    @Override
    public Chapter add(Chapter chapter) {
        if (chapter.getBooks().getId() != 0) {
            Book book = checkIfIdIsPresentandReturnBook(chapter.getBooks().getId());
            chapter.setBooks(book);
            book.addChapter(chapter);
        }

        if (chapter.getAuthor().getId() != 0) {
            Author author = checkIfIdIsPresentandReturnAuthor(chapter.getAuthor().getId());
            chapter.setAuthor(author);
            author.addChapter(chapter);
        }

        if (chapter.getPublisher().getId() != 0) {
            Publisher publisher = checkIfIdIsPresentandReturnPublisher(chapter.getPublisher().getId());
            chapter.setPublisher(publisher);
            publisher.addChapter(chapter);
        }
        chapter.setId(Calendar.AUGUST);
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter update(Chapter chapter, int id) {

        Chapter oldChapter = checkIfIdIsPresentandReturnChapter(id);
        if (chapter.getBooks() != null) {
            Book book;
            if (chapter.getBooks().getId() != 0) {
                book = checkIfIdIsPresentandReturnBook(chapter.getBooks().getId());
                book.addChapter(oldChapter);
            } else {
                book = chapter.getBooks();
                oldChapter.setBooks(book);
            }
        }

        if (chapter.getAuthor() != null) {
            Author author;
            if (chapter.getAuthor().getId() !=0) {
                author = checkIfIdIsPresentandReturnAuthor(chapter.getAuthor().getId());
                author.addChapter(oldChapter);
            } else {
                author = chapter.getAuthor();
                oldChapter.setAuthor(author);
            }
        }

        if (chapter.getPublisher() != null) {
            Publisher publisher;
            if (chapter.getPublisher().getId() !=0) {
                publisher = checkIfIdIsPresentandReturnPublisher(chapter.getPublisher().getId());
                publisher.addChapter(oldChapter);
            } else {
                publisher = chapter.getPublisher();
                oldChapter.setPublisher(publisher);
            }
        }

        if (chapter.getForename() != null) {
            chapter.setForename(chapter.getForename());
        }

        if (chapter.getDescription() != null) {
            chapter.setDescription(chapter.getDescription());
        }

        return chapterRepository.save(oldChapter);
    }

    @Override
    public Chapter getById(int id) {
        return checkIfIdIsPresentandReturnChapter(id);
    }

    @Override
    public Chapter deleteById(int id) {
        Chapter chapter = checkIfIdIsPresentandReturnChapter(id);
        chapterRepository.deleteById(id);
        return null;
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

    private Book checkIfIdIsPresentandReturnBook(int id) {
        if (!bookRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( " Book id=" + id + " not found" );
        else
            return bookRepository.findById(id).get();
    }

    private Chapter checkIfIdIsPresentandReturnChapter(int id) {
        if (!chapterRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( " Chapter id=" + id + " not found" );
        else
            return chapterRepository.findById(id).get();
    }
}
