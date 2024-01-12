package com.aleyyu.library.dto.converter;

import com.aleyyu.library.dto.response.AuthorResponse;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.model.Author;
import com.aleyyu.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    private final AuthorConverter authorConverter;

    public BookConverter(AuthorConverter authorConverter) {
        this.authorConverter = authorConverter;
    }

    public BookResponse convertToBookResponse(Book book){
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setIsbn(book.getIsbn());
        response.setStatus(book.getStatus());
        response.setAuthorName(book.getAuthor().getName());

        return response;
    }

    public Book convertToBook(CreateBookRequest request, Author author){
        Book book = new Book();
        book.setName(request.getName());
        book.setIsbn(request.getIsbn());
        book.setAuthor(author);
        return book;
    }

    public Book convertToBook(BookResponse response){
        Book book = new Book();
        book.setName(response.getName());
        book.setIsbn(response.getIsbn());

        return book;
    }
}
