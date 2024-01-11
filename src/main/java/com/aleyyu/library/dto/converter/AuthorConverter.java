package com.aleyyu.library.dto.converter;

import com.aleyyu.library.dto.request.create.CreateAuthorRequest;
import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public AuthorResponse convertToAuthorResponse(Author author){
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        return response;
    }

    public Author convertToAuthor(CreateAuthorRequest request){
        Author author = new Author();
        author.setName(request.getName());
        return author;
    }
}
