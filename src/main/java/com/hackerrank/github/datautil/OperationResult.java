package com.hackerrank.github.datautil;

/**
 * author: acerbk
 * Date: 2019-06-28
 * Time: 20:50
 */
public class OperationResult {

    private Boolean isSuccessful;

    private Object data;

    private String reason;

    private int statusCode;

    public OperationResult(Boolean isSuccessful, Object data) {
        this.isSuccessful = isSuccessful;
        this.data = data;
    }

    public OperationResult(Boolean isSuccessful, Object data, String reason,int statusCode) {
        this.isSuccessful = isSuccessful;
        this.data = data;
        this.reason = reason;
        this.statusCode = statusCode;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public OperationResult setSuccessful(Boolean successful) {
        isSuccessful = successful;
        return this;
    }

    public Object getData() {
        return data;
    }

    public OperationResult setData(Object data) {
        this.data = data;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public OperationResult setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public OperationResult setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
