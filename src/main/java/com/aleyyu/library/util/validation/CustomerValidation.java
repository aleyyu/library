package com.aleyyu.library.util.validation;

import com.aleyyu.library.dto.request.create.CreateCustomerRequest;
import com.aleyyu.library.dto.request.update.UpdateCustomerRequest;
import com.aleyyu.library.exception.customer.CustomerValidationException;
import com.aleyyu.library.util.helper.StringHelper;
import com.aleyyu.library.util.type.CustomerErrorType;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

    public void validateCustomer(CreateCustomerRequest request) throws CustomerValidationException {

        if(StringHelper.isBlank(request.getName()) || StringHelper.isBlank(request.getLastName())){
            throw new CustomerValidationException(CustomerErrorType.CUSTOMER_NAME_MISSING.getCode(), CustomerErrorType.CUSTOMER_NAME_MISSING.getMessage());
        }
        if(StringHelper.isBlank(request.getLibraryNo())){
            throw new CustomerValidationException(CustomerErrorType.LIBRARY_NO_MISSING.getCode(), CustomerErrorType.LIBRARY_NO_MISSING.getMessage());
        }
        if(StringHelper.isBlank(request.getAddress())){
            throw new CustomerValidationException(CustomerErrorType.ADDRESS_MISSING.getCode(), CustomerErrorType.ADDRESS_MISSING.getMessage());
        }
        if(StringHelper.isBlank(request.getPhoneNumber())){
            throw new CustomerValidationException(CustomerErrorType.PHONE_NUMBER_MISSING.getCode(), CustomerErrorType.PHONE_NUMBER_MISSING.getMessage());
        }
    }
    public void validateCustomer(UpdateCustomerRequest request){

        if(StringHelper.isBlank(request.getName()) || StringHelper.isBlank(request.getLastName())){
            throw new CustomerValidationException(CustomerErrorType.CUSTOMER_NAME_MISSING.getCode(), CustomerErrorType.CUSTOMER_NAME_MISSING.getMessage());
        }
        if(StringHelper.isBlank(request.getLibraryNo())){
            throw new CustomerValidationException(CustomerErrorType.LIBRARY_NO_MISSING.getCode(), CustomerErrorType.LIBRARY_NO_MISSING.getMessage());
        }
        if(StringHelper.isBlank(request.getAddress())){
            throw new CustomerValidationException(CustomerErrorType.ADDRESS_MISSING.getCode(), CustomerErrorType.ADDRESS_MISSING.getMessage());
        }
        if(StringHelper.isBlank(request.getPhoneNumber())){
            throw new CustomerValidationException(CustomerErrorType.PHONE_NUMBER_MISSING.getCode(), CustomerErrorType.PHONE_NUMBER_MISSING.getMessage());
        }
    }
}
