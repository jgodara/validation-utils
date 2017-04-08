package com.corticera.validation.test.validators;

import com.corticera.validation.Result;
import com.corticera.validation.Validator;
import com.corticera.validation.core.StandardResult;

public class AlphaNumericValidtor implements Validator<String> {

	private final Result FAILED;

	public AlphaNumericValidtor() {
		FAILED = new StandardResult("The password should be alphanumeric.", false);
	}

	public Result validate(String value) {
		return isAlphaNumeric(value) ? Result.OK : FAILED;
	}

	private boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		return s.matches(pattern);
	}
	
	@Override
	public String toString() {
		return "AlphaNumericValidator";
	}

}
