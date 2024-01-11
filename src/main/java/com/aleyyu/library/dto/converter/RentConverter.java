package com.aleyyu.library.dto.converter;

import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.response.RentResponse;
import com.aleyyu.library.model.Rent;
import org.springframework.stereotype.Component;

@Component
public class RentConverter {

    public RentResponse convertToRentResponse(Rent rent){
        RentResponse response = new RentResponse();
        response.setCustomerName(rent.getCustomer().getName());
        response.setCustomerLastName(rent.getCustomer().getLastName());
        response.setBookName(response.getBookName());
        response.setStatus(rent.getStatus());
        response.setCheckoutDate(rent.getCheckoutDate());
        response.setReturnDate(rent.getReturnDate());
        return response;
    }

    public Rent convertToRent(CreateRentRequest request){
        Rent rent = new Rent();
        rent.getCustomer().setName(request.getCustomerName());
        rent.getCustomer().setLastName(request.getCustomerLastName());
        rent.getBook().setName(request.getBookName());
        rent.setStatus(request.getStatus());
        rent.setCheckoutDate(request.getCheckoutDate());
        rent.setReturnDate(request.getReturnDate());
        return rent;
    }
}
