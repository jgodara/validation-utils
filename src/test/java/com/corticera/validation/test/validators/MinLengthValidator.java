package com.corticera.validation.test.validators;

import com.corticera.validation.Result;
import com.corticera.validation.Validator;
import com.corticera.validation.core.StandardResult;

public class MinLengthValidator implements Validator<String> {

	private final Result FAILED;
	private Integer minLength;

	public MinLengthValidator() {
		this(8);
	}

	public MinLengthValidator(Integer minLength) {
		this.minLength = minLength;
		FAILED = new StandardResult("Password must be at least " + minLength
				+ " characters long.", false);
	}

	public Result validate(String value) {
		return value.length() >= minLength ? Result.OK : FAILED;
	}
	
	@Override
	public String toString() {
		return "MinLengthValidator";
	}

}
