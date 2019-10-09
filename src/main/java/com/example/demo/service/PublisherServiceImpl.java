package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Chapter;
import com.example.demo.model.Publisher;
import com.example.demo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class PublisherServiceImpl extends PublisherService{

    @Autowired
    PublisherRepository publisherRepository;

    public PublisherServiceImpl() {
    }

    @Override
    public List<Book> getBooksById(int id) {
        return checkIfIdIsPresentandReturnPublisher(id).getBookList();
    }

    @Override
    public List<Chapter> getChapterById(int id) {
        return null;
    }

    @Override
    public Page<Publisher> getAll(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    @Override
    public Publisher add(Publisher o) {
        o.setId(Calendar.AUGUST);
        return publisherRepository.save(o);
    }

    @Override
    public Publisher update(Publisher o, int id) {
        Publisher publisher = checkIfIdIsPresentandReturnPublisher(id);
        publisher.setName(o.getName());
        publisher.setAddress(o.getAddress());
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher getById(int id) {
        return checkIfIdIsPresentandReturnPublisher(id);
    }

    @Override
    public Publisher deleteById(int id) {
        Publisher publisher = checkIfIdIsPresentandReturnPublisher(id);
        publisherRepository.deleteById(id);
        return publisher;
    }

    private Publisher checkIfIdIsPresentandReturnPublisher(int id) {
        if (!publisherRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( " Publisher id = " + id + " not found" );
        else
            return publisherRepository.findById(id).get();
    }
}