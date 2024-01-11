package com.aleyyu.library.service;

import com.aleyyu.library.dto.converter.AuthorConverter;
import com.aleyyu.library.dto.request.create.CreateAuthorRequest;
import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.repository.AuthorRepository;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter converter;

    public AuthorService(AuthorRepository authorRepository, AuthorConverter converter) {
        this.authorRepository = authorRepository;
        this.converter = converter;
    }

    public DataResult<List<AuthorResponse>> getAll(){
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResponse> authorResponseList = new ArrayList<>();
        for(Author author : authorList){
            AuthorResponse response = converter.convertToAuthorResponse(author);
            authorResponseList.add(response);
        }
        return new SuccessDataResult<>(authorResponseList);
    }

    public DataResult<Author> getAuthorById(int id){
        Author author = authorRepository.findById(id);
        return new SuccessDataResult<>(author);
    }

    public Result add(CreateAuthorRequest request){
       Author author = converter.convertToAuthor(request);
       authorRepository.save(author);
       return new SuccessResult("AUTHOR.ADDED");
    }
}
