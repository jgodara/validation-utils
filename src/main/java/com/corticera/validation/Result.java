package com.corticera.validation;

import com.corticera.validation.core.StandardResult;

public interface Result {

	public static final Result OK = new StandardResult();
	
	public Throwable getError();

	public boolean isOk();

	public String getMessage();

}
