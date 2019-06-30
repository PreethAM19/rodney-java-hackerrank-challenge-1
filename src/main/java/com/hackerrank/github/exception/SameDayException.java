package com.hackerrank.github.exception;

/**
 * throws if the day difference in two timestamps is the same ,ie same day
 * author: acerbk
 * Date: 2019-06-30
 * Time: 19:00
 */
public class SameDayException extends RuntimeException{
    public SameDayException(String message) {
        super(message);
    }
}
