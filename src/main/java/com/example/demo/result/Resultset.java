package com.example.demo.result;

import com.example.demo.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Resultset<T> {

    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private T data;

    private Resultset() {
        this.timestamp = LocalDateTime.now();
    }

    public Resultset(T o, int status ) throws ResourceNotFoundException {
        this();
        if ( o == null || ( o instanceof List && ( (List) o ).isEmpty() ) )
            throw new ResourceNotFoundException( "No Content Found" );
        this.status = status;
        this.data = o;
    }
}
