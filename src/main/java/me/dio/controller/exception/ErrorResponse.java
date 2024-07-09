package me.dio.controller.exception;

import org.springframework.http.HttpStatusCode;

public class ErrorResponse {
    public ErrorDetail error;

    public ErrorResponse(String message, HttpStatusCode httpStatus) {
        this.error = new ErrorDetail();
        this.error.setHttpStatusCode(httpStatus.value());
        this.error.setMessage(message);
    }

    public ErrorDetail getError() {
        return error;
    }

}
