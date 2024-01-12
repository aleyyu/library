package com.aleyyu.library.service;

import com.aleyyu.library.dto.request.update.UpdateAuthorRequest;
import com.aleyyu.library.dto.request.update.UpdateBookRequest;
import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.converter.BookConverter;
import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.repository.BookRepository;
import com.aleyyu.library.util.mapper.ModelMapperService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import com.aleyyu.library.util.type.BookStatusType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapperService modelMapperService;

    public BookService(BookRepository bookRepository,
                       ModelMapperService modelMapperService) {
        this.bookRepository = bookRepository;
        this.modelMapperService = modelMapperService;
    }

    public DataResult<List<BookResponse>> getAll(){
        List<Book> bookList = bookRepository.findAll();
        List<BookResponse> responseList = bookList.stream()
                .map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    public DataResult<BookResponse> getBookById(int id){
        Book book = bookRepository.findById(id);
        BookResponse response = modelMapperService.forResponse().map(book, BookResponse.class);
        return new SuccessDataResult<>(response);
    }

    public DataResult<List<BookResponse>> getBookByCustomerLibraryNo(String libraryNo){
        List<Book> bookList = bookRepository.findBookByLibraryNo(libraryNo);
        List<BookResponse> bookResponseList = bookList.stream()
                .map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(bookResponseList);
    }

    public DataResult<List<BookResponse>> getAvailableBook(){
        List<Book> bookList = bookRepository.findByStatus(BookStatusType.AVAILABLE.getCode());
        List<BookResponse> responseList = bookList.stream().map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    public Result add(CreateBookRequest request){
        Book newBook = modelMapperService.forRequest().map(request, Book.class);
        bookRepository.save(newBook);
        return new SuccessResult("BOOK.ADDED");
    }

    public Result update(UpdateBookRequest request){
        bookRepository.save(modelMapperService.forRequest().map(request, Book.class));
        return new SuccessResult("BOOK.UPDATED");
    }

    public Result delete(int id){
        bookRepository.delete(bookRepository.findById(id));
        return new SuccessResult("BOOK.DELETED");
    }
}
