package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Chapter;
import com.example.demo.repository.AuthorRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class AuthorServiceImpl extends AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Book> getBooksById(int id) {
        return checkIfIdIsPresentandReturnAuthor(id).getBookList();
    }

    @Override
    public List<Chapter> getChapterById(int id) {
        return checkIfIdIsPresentandReturnAuthor(id).getChapterList();
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author add(Author o) {
        o.setId(Calendar.AUGUST);
        return authorRepository.save(o);
    }

    @Override
    public Author update(Author o, int id) {
        Author author = checkIfIdIsPresentandReturnAuthor(id);
        author.setName( o.getName() );
        author.setAddress( o.getAddress() );
        return authorRepository.save( author );
    }

    @Override
    public Author getById(int id) {
        return checkIfIdIsPresentandReturnAuthor(id);
    }

    @Override
    public Author deleteById(int id) {
        Author author = checkIfIdIsPresentandReturnAuthor( id );
        authorRepository.deleteById( id );
        return author;
    }

    private Author checkIfIdIsPresentandReturnAuthor( int id )
    {
        if ( !authorRepository.findById( id ).isPresent() )
            throw new ResourceNotFoundException( " Author id = " + id + " not found" );
        else
            return authorRepository.findById( id ).get();
    }
}