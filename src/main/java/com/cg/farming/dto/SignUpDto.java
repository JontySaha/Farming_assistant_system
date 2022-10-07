package com.cg.farming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpDto {
	@NotBlank(message = "Username is mandatory")
	@Size(max = 20)
    private String username;
	@NotBlank(message = "Password is mandatory")
	@Size(max = 50)
    private String password;
    @NotBlank(message = "Address is mandatory")
	@Size(max = 240)
    private String address;
    @NotBlank(message = "Phone Number is mandatory")
	@Size(max = 12)
    private String phoneNo;
    @NotBlank(message = "Name is mandatory")
	@Size(max = 120)
    private String name;
}
