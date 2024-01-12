package com.aleyyu.library.service;

import com.aleyyu.library.dto.converter.AuthorConverter;
import com.aleyyu.library.dto.request.create.CreateAuthorRequest;
import com.aleyyu.library.dto.request.update.UpdateAuthorRequest;
import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.repository.AuthorRepository;
import com.aleyyu.library.util.mapper.ModelMapperService;
import com.aleyyu.library.util.result.DataResult;
import com.aleyyu.library.util.result.Result;
import com.aleyyu.library.util.result.SuccessDataResult;
import com.aleyyu.library.util.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapperService modelMapperService;

    public AuthorService(AuthorRepository authorRepository, ModelMapperService modelMapperService) {
        this.authorRepository = authorRepository;
        this.modelMapperService = modelMapperService;
    }

    public DataResult<List<AuthorResponse>> getAll(){
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResponse> authorResponseList = authorList.stream()
                .map(author -> modelMapperService.forResponse().map(author, AuthorResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(authorResponseList);
    }

    public DataResult<AuthorResponse> getAuthorById(int id){
        AuthorResponse response = modelMapperService.forResponse().map(authorRepository.findById(id), AuthorResponse.class);
        return new SuccessDataResult<>(response);
    }

    public Result add(CreateAuthorRequest request){
       Author newAuthor= modelMapperService.forRequest().map(request, Author.class);
       authorRepository.save(newAuthor);
       return new SuccessResult("AUTHOR.ADDED");
    }

    public Result update(UpdateAuthorRequest request){
        authorRepository.save(modelMapperService.forRequest().map(request, Author.class));
        return new SuccessResult("AUTHOR.UPDATED");
    }

    public Result delete(int id){
        authorRepository.delete(authorRepository.findById(id));
        return new SuccessResult("AUTHOR.DELETED");
    }

}
