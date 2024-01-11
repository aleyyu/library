package com.aleyyu.library.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private int id;
    private String name;
    private String lastName;
    private String libraryNo;
    private String address;
    private String phoneNumber;
}
