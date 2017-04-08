package com.corticera.validation;

public interface Validator<T> {
	
	public Result validate(T target);

}
