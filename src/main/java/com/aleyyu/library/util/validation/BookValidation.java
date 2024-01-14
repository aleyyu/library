package com.aleyyu.library.util.validation;

import com.aleyyu.library.dto.request.create.CreateBookRequest;
import com.aleyyu.library.dto.request.update.UpdateBookRequest;
import com.aleyyu.library.dto.response.BookResponse;
import com.aleyyu.library.exception.book.BookValidationException;
import com.aleyyu.library.model.Book;
import com.aleyyu.library.util.type.BookErrorType;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookValidation {

    public void validateBook(Book book){
        if(Objects.isNull(book)){
            throw new BookValidationException(BookErrorType.BOOK_DOES_NOT_EXIST.getCode(), BookErrorType.BOOK_DOES_NOT_EXIST.getMessage());
        }
    }

    public void validateBook(CreateBookRequest request){
        if(Objects.isNull(request)){
            throw new BookValidationException(BookErrorType.BOOK_DOES_NOT_EXIST.getCode(), BookErrorType.BOOK_DOES_NOT_EXIST.getMessage());
        }else {
            if(Objects.nonNull(request.getIsbn())){
                throw new BookValidationException(BookErrorType.BOOK_BY_ISBN_ALREADY_EXISTS.getCode(), BookErrorType.BOOK_BY_ISBN_ALREADY_EXISTS.getMessage());
            }
        }
    }

    public void validateBook(UpdateBookRequest request){
        if(Objects.isNull(request)){
            throw new BookValidationException(BookErrorType.BOOK_DOES_NOT_EXIST.getCode(), BookErrorType.BOOK_DOES_NOT_EXIST.getMessage());
        }
    }

    public void validateBook(BookResponse response){
        if(Objects.isNull(response)){
            throw new BookValidationException(BookErrorType.BOOK_DOES_NOT_EXIST.getCode(), BookErrorType.BOOK_DOES_NOT_EXIST.getMessage());
        }
    }
}
