package com.aleyyu.library.controller;

import com.aleyyu.library.dto.request.update.UpdateBookRequest;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.service.BookService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getById/{id}")
    public ResponseEntity<DataResult<BookResponse>> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/getByLibraryNo/{libraryNo}")
    public ResponseEntity<DataResult<List<BookResponse>>> getByLibraryNo(@PathVariable("libraryNo") String libraryNo){
        return ResponseEntity.ok(bookService.getBookByCustomerLibraryNo(libraryNo));
    }

    @GetMapping("/getAvailable")
    public ResponseEntity<DataResult<List<BookResponse>>> getByAvailable(){
        return ResponseEntity.ok(bookService.getAvailableBook());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(CreateBookRequest request){
        return ResponseEntity.ok(bookService.add(request));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id){
        return ResponseEntity.ok(bookService.delete(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid UpdateBookRequest request){
        return ResponseEntity.ok(bookService.update(request));
    }

}
