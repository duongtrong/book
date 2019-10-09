package com.example.demo.controller;

import com.example.demo.model.Chapter;
import com.example.demo.result.ResponseWrapper;
import com.example.demo.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import static com.example.demo.constants.ApiConstants.MESSAGE_FOR_REGEX_NUMBER_MISMATCH;
import static com.example.demo.constants.ApiConstants.REGEX_FOR_NUMBERS;

@RestController
@RequestMapping("/api/v1/book/{id}/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseWrapper<Chapter> getChapterById(
            @Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH)
            @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(chapterService.getById(Integer.parseInt(id)), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseWrapper<Page<Chapter>> getChapterAll(Pageable pageable) {
        return new ResponseWrapper<>(chapterService.getAll(pageable), HttpStatus.OK.value());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseWrapper<Chapter> createChapter(@Valid @RequestBody Chapter chapter) {
        return new ResponseWrapper<>(chapterService.add(chapter), HttpStatus.CREATED.value());
    }
}
