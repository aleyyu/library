package com.aleyyu.library.controller;

import com.aleyyu.library.dto.request.create.CreateAuthorRequest;
import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.service.AuthorService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorsController {

    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<AuthorResponse>>> getAll(){
        DataResult<List<AuthorResponse>> authorResponseList = authorService.getAll();
        return ResponseEntity.ok(authorResponseList);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(CreateAuthorRequest request){
        return ResponseEntity.ok(authorService.add(request));
    }

}
