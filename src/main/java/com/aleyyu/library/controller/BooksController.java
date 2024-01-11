package com.aleyyu.library.controller;

import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.service.BookService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<BookResponse>>> getAll(){
        DataResult<List<BookResponse>> bookResponseList = bookService.getAll();
        return ResponseEntity.ok(bookResponseList);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(CreateBookRequest request){
        return ResponseEntity.ok(bookService.add(request));
    }
}
