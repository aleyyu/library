package com.aleyyu.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentResponse {

    private int rentId;
    private int customerId;
    private int bookId;
    private String status;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

}
