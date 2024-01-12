package com.aleyyu.library.dto.converter;

import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.response.RentResponse;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.model.Customer;
import com.aleyyu.library.model.Rent;
import org.springframework.stereotype.Component;

@Component
public class RentConverter {

    public RentResponse convertToRentResponse(Rent rent){
        RentResponse response = new RentResponse();
        response.setCustomerId(rent.getCustomer().getId());
        response.setBookId(rent.getBook().getId());
        response.setStatus(rent.getStatus());
        response.setCheckoutDate(rent.getCheckoutDate());
        response.setReturnDate(rent.getReturnDate());
        return response;
    }

}
