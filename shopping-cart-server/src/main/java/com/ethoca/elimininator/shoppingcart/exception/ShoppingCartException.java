package com.ethoca.elimininator.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class ShoppingCartException extends RuntimeException {

	private static final long serialVersionUID = -4719006528268629819L;

	private final HttpStatus httpStatus;
	private final transient ErrorResponse errorResponse;

	/**
	 * Create a ShoppingCartException instance with httpStatus and headers
	 * information.
	 *
	 * @param errorResponse POJO for error message retrieved from downstream
	 * @param httpStatus    httpStatus information
	 */
	public ShoppingCartException(ErrorResponse errorResponse, HttpStatus httpStatus) {
		super(errorResponse.toString());
		this.errorResponse = errorResponse;
		this.httpStatus = httpStatus;
	}
}
