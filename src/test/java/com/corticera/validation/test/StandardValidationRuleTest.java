package com.corticera.validation.test;

import com.corticera.validation.Result;
import com.corticera.validation.Validator;
import com.corticera.validation.core.ChainValidator;
import com.corticera.validation.test.validators.AlphaNumericValidtor;
import com.corticera.validation.test.validators.MinLengthValidator;

public class StandardValidationRuleTest {

	public static void main(String[] args) {
		Validator<String> passwordRule = new ChainValidator<String>(
				new MinLengthValidator(), new AlphaNumericValidtor(), new Validator() {

					public Result validate(Object target) {
						String s = null;
						s.length();
						return Result.OK;
					}
					
				});
		Result result = passwordRule.validate("Test12345");

		if (result.isOk()) {
			System.out.println("Validation passed!");
		} else {
			System.out.println("Validation failed: " + result.getMessage());
			result.getError().printStackTrace();
		}
	}

}
