package com.aleyyu.library.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private int id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String libraryNo;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private String phoneNumber;
}
