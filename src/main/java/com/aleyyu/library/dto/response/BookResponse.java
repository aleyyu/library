package com.aleyyu.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private int id;
    private String name;
    private String isbn;
    private String authorName;
    private String status;
}
