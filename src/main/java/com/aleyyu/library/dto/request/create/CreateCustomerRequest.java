package com.aleyyu.library.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private String phoneNumber;

    @NotBlank
    @NotNull
    private String libraryNo;

}
