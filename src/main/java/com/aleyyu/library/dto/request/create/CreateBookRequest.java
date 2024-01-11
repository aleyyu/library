package com.aleyyu.library.dto.request.create;

import com.aleyyu.library.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 13, max = 13)
    private String isbn;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 1)
    private String status;

    @NotBlank
    @NotNull
    private int authorId;
}
