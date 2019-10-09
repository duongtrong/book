package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.result.ResponseWrapper;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.util.List;

import static com.example.demo.constants.ApiConstants.MESSAGE_FOR_REGEX_NUMBER_MISMATCH;
import static com.example.demo.constants.ApiConstants.REGEX_FOR_NUMBERS;

@RestController
@Validated
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseWrapper<Author> getAuthorById(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id ) {
        return new ResponseWrapper<>(authorService.getById(Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseWrapper<Page<Author>> getAuthorAll(Pageable pageable ) {
        return new ResponseWrapper<>( authorService.getAll(pageable), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseWrapper<Author> createAuthor( @Valid @RequestBody Author author ) {
        return new ResponseWrapper<>( authorService.add( author ), HttpStatus.CREATED.value());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseWrapper<Author> deleteAuthor(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id ) {
        return new ResponseWrapper<>( authorService.deleteById( Integer.parseInt( id ) ), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseWrapper<Author> UpdateAuthor(
            @Valid @RequestBody Author author,
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id ) {
        return new ResponseWrapper<>( authorService.update( author, Integer.parseInt( id ) ), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/books")
    public ResponseWrapper<List<Book>> getAuthorBooksById(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id ) {
        return new ResponseWrapper<>( authorService.getBooksById( Integer.parseInt( id ) ), HttpStatus.OK.value());
    }
}
