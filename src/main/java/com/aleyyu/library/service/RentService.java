package com.aleyyu.library.service;

import com.aleyyu.library.dto.converter.BookConverter;
import com.aleyyu.library.dto.converter.CustomerConverter;
import com.aleyyu.library.dto.converter.RentConverter;
import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.request.update.EndRentRequest;
import com.aleyyu.library.dto.request.update.UpdateAuthorRequest;
import com.aleyyu.library.dto.request.update.UpdateRentRequest;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.dto.response.RentResponse;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.model.Customer;
import com.aleyyu.library.model.Rent;
import com.aleyyu.library.repository.RentRepository;
import com.aleyyu.library.util.mapper.ModelMapperService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import com.aleyyu.library.util.type.BookStatusType;
import com.aleyyu.library.util.type.RentStatusType;
import com.aleyyu.library.util.validation.RentValidation;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ModelMapperService modelMapperService;
    private final BookService bookService;
    private final RentValidation rentValidation;

    public RentService(RentRepository rentRepository,
                       ModelMapperService modelMapperService, BookService bookService, RentValidation rentValidation) {
        this.rentRepository = rentRepository;
        this.modelMapperService = modelMapperService;
        this.bookService = bookService;
        this.rentValidation = rentValidation;
    }

    public DataResult<List<RentResponse>> getAll(){
        List<Rent> rentList = rentRepository.findAll();
        List<RentResponse> rentResponseList = rentList.stream()
                .map(rent -> modelMapperService.forResponse().map(rent, RentResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(rentResponseList);
    }

    public DataResult<RentResponse> getRentById(int id){
        Rent rent = rentRepository.findById(id);
        rentValidation.validateRent(rent);
        RentResponse response = modelMapperService.forResponse().map(rent, RentResponse.class);
        return new SuccessDataResult<>(response);
    }

    public DataResult<RentResponse> getRentByBookId(int id){
        Rent rent = rentRepository.findByBookId(id);
        rentValidation.validateRent(rent);
        return new SuccessDataResult<>(modelMapperService.forResponse().map(rent, RentResponse.class));
    }

    public Result add(CreateRentRequest request){
        Rent newRent =  modelMapperService.forRequest().map(request, Rent.class);
        Book book = newRent.getBook();
        book.setStatus(BookStatusType.UNAVAILABLE.getCode());
        newRent.setStatus(RentStatusType.NOT_RETURNED.getCode());
        rentRepository.save(newRent);
        return new SuccessResult("RENT.ADDED");
    }

    public Result update(UpdateRentRequest request){
        rentValidation.validateRent(request);
        rentRepository.save(modelMapperService.forRequest().map(request, Rent.class));
        return new SuccessResult("RENT.UPDATED");
    }

    public Result endRent(int id){
        Rent endedRent = rentRepository.findById(id);
        rentValidation.validateRent(endedRent);
        Book returnedBook = getBookByRent(endedRent.getBook().getId());
        returnedBook.setStatus(BookStatusType.AVAILABLE.getCode());
        endedRent.setStatus(RentStatusType.RETURNED.getCode());
        endedRent.setReturnDate(LocalDate.now());
        rentRepository.save(endedRent);
        return new SuccessResult("RENT.ENDED");
    }

    public Result delete(int id){
        Rent rent = rentRepository.findById(id);
        rentValidation.validateRent(rent);
        rentRepository.delete(rent);
        return new SuccessResult("RENT.DELETED");
    }

    private Book getBookByRent(int bookId){
        Book book = modelMapperService.forResponse().map(bookService.getBookById(bookId), Book.class);
        return book;
    }
}
