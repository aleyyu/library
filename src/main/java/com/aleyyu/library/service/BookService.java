package com.aleyyu.library.service;

import com.aleyyu.library.dto.request.update.UpdateAuthorRequest;
import com.aleyyu.library.dto.request.update.UpdateBookRequest;
import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.converter.BookConverter;
import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.repository.BookRepository;
import com.aleyyu.library.util.helper.DateHelper;
import com.aleyyu.library.util.mapper.ModelMapperService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import com.aleyyu.library.util.type.BookStatusType;
import com.aleyyu.library.util.validation.BookValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapperService modelMapperService;
    private final BookValidation validation;

    public BookService(BookRepository bookRepository,
                       ModelMapperService modelMapperService, BookValidation validation) {
        this.bookRepository = bookRepository;
        this.modelMapperService = modelMapperService;
        this.validation = validation;
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
        validation.validateBook(book);
        BookResponse response = modelMapperService.forResponse().map(book, BookResponse.class);
        return new SuccessDataResult<>(response);
    }

    public DataResult<List<BookResponse>> getBookByCustomerLibraryNo(String libraryNo){
        List<Book> bookList = bookRepository.findBookByLibraryNo(libraryNo);
        List<BookResponse> bookResponseList = bookList.stream()
                .peek(validation::validateBook)
                .map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(bookResponseList);
    }

    public DataResult<List<BookResponse>> getAvailableBook(){
        List<Book> bookList = bookRepository.findByStatus(BookStatusType.AVAILABLE.getCode());
        List<BookResponse> responseList = bookList.stream()
                .peek(validation::validateBook)
                .map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    public DataResult<List<BookResponse>> getBooksCustomerEverRead(int id){
        List<Book> bookList = bookRepository.findBooksCustomerEverRead(id);
        List<BookResponse> responseList = bookList.stream()
                .peek(validation::validateBook)
                .map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    public DataResult<List<BookResponse>> getBooksReadByCustomerBetweenDates(int customerId, String dateStart, String dateEnd){
        List<Book> bookList = bookRepository.findBooksBetweenDate(customerId, DateHelper.convert(dateStart), DateHelper.convert(dateEnd));
        List<BookResponse> responseList = bookList.stream()
                .map(book -> modelMapperService.forResponse().map(book, BookResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    public Result add(CreateBookRequest request){
        validation.validateBook(request);
        Book newBook = modelMapperService.forRequest().map(request, Book.class);
        bookRepository.save(newBook);
        return new SuccessResult("BOOK.ADDED");
    }

    public Result update(UpdateBookRequest request){
        validation.validateBook(request);
        bookRepository.save(modelMapperService.forRequest().map(request, Book.class));
        return new SuccessResult("BOOK.UPDATED");
    }

    public Result delete(int id){
        Book book = bookRepository.findById(id);
        validation.validateBook(book);
        bookRepository.delete(book);
        return new SuccessResult("BOOK.DELETED");
    }
}
