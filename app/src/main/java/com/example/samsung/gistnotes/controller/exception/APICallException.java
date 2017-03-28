package com.example.samsung.gistnotes.controller.exception;

import android.content.res.Resources;
import android.view.View;

import com.example.samsung.gistnotes.R;
import com.example.samsung.gistnotes.controller.AllInOneActivity;

/**
 * Created by btow on 25.03.2017.
 */

public class APICallException extends Exception {

    private String message = Resources.getSystem().getString(R.string.message_about_the_empty_response);

    public APICallException() {
        new APICallException(this.message);
    }

    public APICallException(String message) {
        this.message = message;
    }

}
