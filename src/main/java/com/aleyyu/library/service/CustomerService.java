package com.aleyyu.library.service;

import com.aleyyu.library.dto.request.create.CreateCustomerRequest;
import com.aleyyu.library.dto.request.update.UpdateCustomerRequest;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.dto.converter.CustomerConverter;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.model.Customer;
import com.aleyyu.library.model.Rent;
import com.aleyyu.library.repository.CustomerRepository;
import com.aleyyu.library.util.mapper.ModelMapperService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import com.aleyyu.library.util.validation.CustomerValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerValidation validation;
    private final RentService rentService;

    public CustomerService(CustomerRepository customerRepository, ModelMapperService modelMapperService, CustomerValidation validation, RentService rentService) {
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
        this.validation = validation;
        this.rentService = rentService;
    }

    public DataResult<List<CustomerResponse>> getAll(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponse> customerResponseList = customerList.stream()
                .map(customer -> modelMapperService.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(customerResponseList);
    }

    public DataResult<CustomerResponse> getCustomerById(int id){
        CustomerResponse response = modelMapperService.forResponse().map(customerRepository.findById(id), CustomerResponse.class);
        return new SuccessDataResult<>(response);
    }

    public DataResult<CustomerResponse> getCustomerByBookId(int bookId){
        Rent rent =  modelMapperService.forResponse().map(rentService.getRentByBookId(bookId), Rent.class);
        Customer customer = customerRepository.findById(rent.getCustomer().getId());
        return new SuccessDataResult<>(modelMapperService.forResponse().map(customer, CustomerResponse.class));
    }

    public DataResult<List<CustomerResponse>> getCustomerWhoHadNoBook(){
        List<Customer> customerList = customerRepository.findWhoHadNoBook();
        List<CustomerResponse> responseList = customerList.stream()
                .map(customer -> modelMapperService.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responseList);
    }

    public DataResult<CustomerResponse> getCustomerWithMostBook(){
        Customer customer = customerRepository.findWhoHasMostBook();
        CustomerResponse response = modelMapperService.forResponse().map(customer, CustomerResponse.class);
        return new SuccessDataResult<>(response);
    }

    public Result add(CreateCustomerRequest customerRequest){
        validation.validateCustomer(customerRequest);
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setLibraryNo(customerRequest.getLibraryNo());
        customerRepository.save(customer);
        return new SuccessResult("CUSTOMER.ADDED");
    }

    public Result delete(int id){
        Customer customer = customerRepository.findById(id);
        customerRepository.delete(customer);
        return new SuccessResult("CUSTOMER.DELETED");
    }

    public Result update(UpdateCustomerRequest request){
        validation.validateCustomer(request);
        Customer updatedCustomer = modelMapperService.forRequest().map(request, Customer.class);
        customerRepository.save(updatedCustomer);
        return new SuccessResult("CUSTOMER.UPDATED");
    }

}
