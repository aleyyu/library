package com.aleyyu.library.service;

import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.converter.BookConverter;
import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.repository.BookRepository;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter converter;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, BookConverter converter, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.converter = converter;
        this.authorService = authorService;
    }

    public DataResult<List<BookResponse>> getAll(){
        List<Book> bookList = bookRepository.findAll();
        List<BookResponse> responseList = new ArrayList<>();
        for(Book book : bookList){
            BookResponse response = converter.convertToBookResponse(book);
            responseList.add(response);
        }
        return new SuccessDataResult<>(responseList);
    }

    public Result add(CreateBookRequest request){
        Author author = authorService.getAuthorById(request.getAuthorId()).getData();
        Book book = converter.convertToBook(request, author);
        bookRepository.save(book);
        return new SuccessResult("BOOK.ADDED");
    }
}
