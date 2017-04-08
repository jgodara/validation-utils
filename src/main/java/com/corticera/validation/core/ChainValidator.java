package com.corticera.validation.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corticera.validation.Result;
import com.corticera.validation.Validator;

public final class ChainValidator<T> implements Validator<T> {

	private final List<Validator<T>> delegate = new ArrayList<Validator<T>>();

	public ChainValidator(Collection<Validator<T>> rules) {
		delegate.addAll(rules);
	}

	public ChainValidator(Validator<T>... rules) {
		delegate.addAll(Arrays.asList(rules));
	}

	public CompositeResult<T> validate(T target) {
		CompositeResult<T> result = new CompositeResult<T>();
		for (Validator<T> v : delegate) {
			Result validationResult = null;
			try {
				validationResult = v.validate(target);
			} catch (Exception ex) {
				validationResult = new StandardResult(ex);
			}
			result.put(v, validationResult);
		}
		return result;
	}

	@Override
	public String toString() {
		String detail = getClass().getName() + "{\n";
		for (Validator<T> v : delegate) {
			detail += "  " + v + "\n";
		}
		detail += "}";
		return detail;
	}

	private final class CompositeResult<T> implements Result {

		private final Map<Validator<T>, Result> delegate = new HashMap<Validator<T>, Result>();	
		private Throwable failCause;

		public boolean isOk() {
			for (Result r : delegate.values()) {
				if (!r.isOk()) {
					failCause = r.getError();
					return false;
				}
			}
			return true;
		}
		

		public String getMessage() {
			return toString();
		}
		
		public void put(Validator<T> validator, Result resut) {
			delegate.put(validator, resut);
		}

		@Override
		public String toString() {
			String details = getClass().getName() + "{\n";
			for (Validator<T> v : delegate.keySet()) {
				details += "  " + v + "=" + delegate.get(v) + "\n";
			}
			details += "}";
			return details;
		}

		public Throwable getError() {
			return failCause;
		}

	}

}