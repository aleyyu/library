package com.aleyyu.library.controller;

import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.request.update.UpdateRentRequest;
import com.aleyyu.library.dto.response.RentResponse;
import com.aleyyu.library.service.RentService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentsController {

    private final RentService rentService;

    public RentsController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<RentResponse>>> getAll(){
        DataResult<List<RentResponse>> rentResponseList = rentService.getAll();
        return ResponseEntity.ok(rentResponseList);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DataResult<RentResponse>> getRentById(@PathVariable("id") int id){
        DataResult<RentResponse> rentResponse = rentService.getRentById(id);
        return ResponseEntity.ok(rentResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid CreateRentRequest request){
        return ResponseEntity.ok(rentService.add(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(UpdateRentRequest request){
        return ResponseEntity.ok(rentService.update(request));
    }

    @PostMapping("/endRent/{id}")
    public ResponseEntity<Result> endRent(@PathVariable("id") int id){
        return ResponseEntity.ok(rentService.endRent(id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Result> deleteRent(@PathVariable("id") int id){
        return ResponseEntity.ok(rentService.delete(id));
    }
}
