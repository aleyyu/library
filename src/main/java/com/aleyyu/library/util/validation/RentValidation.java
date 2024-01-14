package com.aleyyu.library.util.validation;

import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.request.update.UpdateRentRequest;
import com.aleyyu.library.exception.book.BookValidationException;
import com.aleyyu.library.exception.rent.RentValidationException;
import com.aleyyu.library.model.Rent;
import com.aleyyu.library.util.type.BookErrorType;
import com.aleyyu.library.util.type.BookStatusType;
import com.aleyyu.library.util.type.RentErrorType;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentValidation {

    public void validateRent(Rent rent){
        if(Objects.isNull(rent)){
            throw new RentValidationException(RentErrorType.RENT_NOT_EXIST.getCode(), RentErrorType.RENT_NOT_EXIST.getMessage());
        }else {
            if(rent.getBook().getStatus().equals(BookStatusType.UNAVAILABLE)){
                throw new BookValidationException(BookErrorType.BOOK_UNAVAILABLE.getCode(), BookErrorType.BOOK_UNAVAILABLE.getMessage());
            }
        }
    }

    public void validateRent(UpdateRentRequest request){
        if(Objects.isNull(request)){
            throw new RentValidationException(RentErrorType.RENT_NOT_EXIST.getCode(), RentErrorType.RENT_NOT_EXIST.getMessage());
        }
    }
}
