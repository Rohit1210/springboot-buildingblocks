package com.stacksimplify.restservices.Exceptions;

import java.util.Date;

//Simple custom error details bean
public class CustomErrorDetails {

    public Date timestamp;
    public String message;
    public String errorDetails;

    public CustomErrorDetails(Date timestamp, String message1, String errorDetails) {
        super();
        this.timestamp = timestamp;
        this.message = message1;
        this.errorDetails = errorDetails;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
