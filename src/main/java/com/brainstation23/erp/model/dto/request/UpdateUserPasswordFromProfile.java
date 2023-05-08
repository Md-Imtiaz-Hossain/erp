package com.brainstation23.erp.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateUserPasswordFromProfile {
	@NotEmpty(message = "Password is required.")
	@Size(min = 4, max = 32, message = "Password must be between 4 to 32 characters.")
	private String password;
}
