package com.aleyyu.library.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentRequest {

    @NotBlank
    @NotNull
    private int customerId;

    @NotBlank
    @NotNull
    private int bookId;

    private LocalDate checkoutDate;

}
