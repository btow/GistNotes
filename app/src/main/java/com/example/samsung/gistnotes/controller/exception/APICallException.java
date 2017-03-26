package com.example.samsung.gistnotes.controller.exception;

/**
 * Created by btow on 25.03.2017.
 */

public class APICallException extends Exception {

    private String message;

    public APICallException(String message) {
        this.message = message;
    }

}
