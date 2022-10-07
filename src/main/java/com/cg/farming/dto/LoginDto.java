package com.cg.farming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDto {
	@NotBlank(message = "Username is mandatory")
	@Size(max = 20)
    private String username;
	@NotBlank(message = "Password is mandatory")
	@Size(max = 50)
    private String password;
}