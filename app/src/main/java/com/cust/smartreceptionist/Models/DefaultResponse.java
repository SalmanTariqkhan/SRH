package com.cust.smartreceptionist.Models;

public class DefaultResponse {
    boolean error;
    String message;

    public DefaultResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }


}
