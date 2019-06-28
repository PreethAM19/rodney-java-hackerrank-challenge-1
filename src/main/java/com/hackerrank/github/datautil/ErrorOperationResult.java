package com.hackerrank.github.datautil;

/**
 * author: acerbk
 * Date: 2019-06-28
 * Time: 21:04
 */
public class ErrorOperationResult extends OperationResult {
    public ErrorOperationResult(Object data, String reason,int statusCode) {
        super(false, data, reason,statusCode);
    }
}
