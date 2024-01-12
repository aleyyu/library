package com.aleyyu.library.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentRequest {

    private int id;
    private int bookId;
    private int customerId;
    private LocalDate checkoutDate;
}
