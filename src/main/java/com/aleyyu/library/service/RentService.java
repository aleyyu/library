package com.aleyyu.library.service;

import com.aleyyu.library.dto.converter.RentConverter;
import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.response.RentResponse;
import com.aleyyu.library.model.Rent;
import com.aleyyu.library.repository.RentRepository;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final RentConverter converter;

    public RentService(RentRepository rentRepository, RentConverter converter) {
        this.rentRepository = rentRepository;
        this.converter = converter;
    }

    public DataResult<List<RentResponse>> getAll(){
        List<Rent> rentList = rentRepository.findAll();
        List<RentResponse> rentResponseList = new ArrayList<>();
        for(Rent rent : rentList){
            RentResponse response = converter.convertToRentResponse(rent);
            rentResponseList.add(response);
        }
        return new SuccessDataResult<>(rentResponseList);
    }

    public Result add(CreateRentRequest request){
        Rent rent = converter.convertToRent(request);
        rentRepository.save(rent);
        return new SuccessResult("RENT.ADDED");
    }
}
