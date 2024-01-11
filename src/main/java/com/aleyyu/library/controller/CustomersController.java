package com.aleyyu.library.controller;

import com.aleyyu.library.dto.request.create.CreateCustomerRequest;
import com.aleyyu.library.dto.request.update.UpdateCustomerRequest;
import com.aleyyu.library.dto.response.CustomerResponse;
import com.aleyyu.library.service.CustomerService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<CustomerResponse>>> getAll(){
        DataResult<List<CustomerResponse>> customerResponseList = customerService.getAll();
        return ResponseEntity.ok(customerResponseList);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid CreateCustomerRequest request){
        return ResponseEntity.ok(customerService.add(request));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id){
        return ResponseEntity.ok(customerService.delete(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid UpdateCustomerRequest request){
        return ResponseEntity.ok(customerService.update(request));
    }
}
