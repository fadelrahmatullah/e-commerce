package com.app.e_commerce.Source.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.app.e_commerce.Source.constants.ResponseStatus;

import lombok.Data;
import lombok.NonNull;

@Data
public class Response<T> {
	@NonNull
	private ResponseStatus status;
	private String messageCode;
	private String message;
	@NonNull
	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date processFinishDate;
	private T data;

}
