package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Chapter;
import com.example.demo.result.ResponseWrapper;
import com.example.demo.service.BookService;
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

@Validated
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseWrapper<Book> getBookById(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(bookService.getById(Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseWrapper<Page<Book>> getBookAll(Pageable pageable) {
        return new ResponseWrapper<>(bookService.getAll(pageable), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseWrapper<Book> createBook(@Valid @RequestBody Book book) {
        return new ResponseWrapper<>(bookService.add(book), HttpStatus.CREATED.value());
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseWrapper<Book> updateBook(@Valid @RequestBody Book book,
                                              @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
                                              @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(bookService.update(book, Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseWrapper<Book> deleteBook(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
                                                     @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(bookService.deleteById(Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getChapter/{id}")
    public ResponseWrapper<List<Chapter>> getChapterById(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "id") String id){
        return new ResponseWrapper<>(bookService.getChapterById(Integer.parseInt(id)), HttpStatus.OK.value());
    }
}
