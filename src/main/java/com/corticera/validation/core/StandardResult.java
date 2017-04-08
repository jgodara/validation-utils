package com.corticera.validation.core;

import com.corticera.validation.Result;

public final class StandardResult implements Result {

	private String message;
	private boolean isOk;
	private Throwable error;

	public StandardResult() {
		this(null, true);
	}

	public StandardResult(boolean isOk) {
		this(null, isOk);
	}
	
	public StandardResult(Throwable error) {
		this("An exception caused the validation to fail: " + error, false);
		this.error = error;
	}
	
	public StandardResult(String message, Throwable error) {
		this(message, false);
		this.error = error;
	}

	public StandardResult(String message, boolean isOk) {
		if (message != null && message.length() > 0)
			this.message = message;
		else
			this.message = "Validation " + (isOk ? "Successful" : "Failed");

		this.isOk = isOk;
	}

	public boolean isOk() {
		return isOk;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "StandardResult[" + (isOk ? "passed" : "failed") + ", " + getMessage() + "]";
	}

	public Throwable getError() {
		return error;
	}

}
