package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.result.ResponseWrapper;
import com.example.demo.service.PublisherService;
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
@RequestMapping("/api/v1/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseWrapper<Publisher> getPublisherById(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return new ResponseWrapper<>(publisherService.getById(Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseWrapper<Page<Publisher>> getPublisherAll(Pageable pageable) {
        return new ResponseWrapper<>(publisherService.getAll(pageable), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseWrapper<Publisher> createPublisher(@Valid @RequestBody Publisher publisher)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(publisherService.add(publisher), HttpStatus.CREATED.value());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseWrapper<Publisher> deletePublisher(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return new ResponseWrapper<>(publisherService.deleteById(Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseWrapper<Publisher> UpdateAuthor(
            @Valid @RequestBody Publisher publisher,
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return new ResponseWrapper<>(publisherService.update(publisher, Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/books")
    public ResponseWrapper<List<Book>> getPublisherBooksById(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return new ResponseWrapper<>(publisherService.getBooksById(Integer.parseInt(id)), HttpStatus.OK.value());
    }
}
