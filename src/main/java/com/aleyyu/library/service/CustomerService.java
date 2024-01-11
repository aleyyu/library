package com.aleyyu.library.service;

import com.aleyyu.library.dto.request.create.CreateCustomerRequest;
import com.aleyyu.library.dto.request.update.UpdateCustomerRequest;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.dto.converter.CustomerConverter;
import com.aleyyu.library.model.Customer;
import com.aleyyu.library.repository.CustomerRepository;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public DataResult<List<CustomerResponse>> getAll(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        for(Customer customer : customerList){
            CustomerResponse response = converter.convertToCustomerResponse(customer);
            customerResponseList.add(response);
        }
        return new SuccessDataResult<>(customerResponseList);
    }

    public Result add(CreateCustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setLibaryNo(customerRequest.getLibraryNo());
        customerRepository.save(customer);
        return new SuccessResult("CUSTOMER.ADDED");
    }

    public Result delete(int id){
        Customer customer = customerRepository.findById(id);
        customerRepository.delete(customer);
        return new SuccessResult("CUSTOMER.DELETED");
    }

    public Result update(UpdateCustomerRequest request){
        Customer updatedCustomer = converter.convertToCustomer(request);
        customerRepository.save(updatedCustomer);
        return new SuccessResult("CUSTOMER.UPDATED");
    }
}
