package com.mycom.application.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorAuthDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 496454161642156374L;
	
	@JsonProperty("error")
	private String error;
	@JsonProperty("error_description")
	private String errorDescription;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	
}
