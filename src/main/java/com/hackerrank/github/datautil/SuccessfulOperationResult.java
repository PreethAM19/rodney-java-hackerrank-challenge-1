package com.hackerrank.github.datautil;

import com.hackerrank.github.datautil.OperationResult;

/**
 * author: acerbk
 * Date: 2019-06-28
 * Time: 21:01
 */
public class SuccessfulOperationResult extends OperationResult {
    public SuccessfulOperationResult(Object data, String reason,int statusCode) {
        super(true, data, reason,statusCode);
    }
}
