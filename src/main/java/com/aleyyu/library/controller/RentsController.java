package com.aleyyu.library.controller;

import com.aleyyu.library.dto.request.create.CreateRentRequest;
import com.aleyyu.library.dto.response.RentResponse;
import com.aleyyu.library.service.RentService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public ResponseEntity<Result> add(CreateRentRequest request){
        return ResponseEntity.ok(rentService.add(request));
    }
}
