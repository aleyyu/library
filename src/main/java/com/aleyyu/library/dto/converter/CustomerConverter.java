package com.aleyyu.library.dto.converter;

import com.aleyyu.library.dto.request.update.UpdateCustomerRequest;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerResponse convertToCustomerResponse(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setName(customer.getName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setAddress(customer.getAddress());
        customerResponse.setLibraryNo(customer.getLibaryNo());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        return customerResponse;
    }

    public Customer convertToCustomer(UpdateCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setLastName(request.getLastName());
        customer.setLibaryNo(request.getLibraryNo());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        return customer;
    }
}
