package com.authorize.main.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


// ValidatingDTO Example
//{
//	validStatus:false
//}

public class VaildatingDTO {
	private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	@Override
	public String toString() {
		return "VaildatingDTO [validStatus=" + validStatus + "]";
	}

	public VaildatingDTO(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

	public VaildatingDTO() {
		super();
	}

}
