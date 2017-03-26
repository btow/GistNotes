
package com.example.samsung.gistnotes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Files {

    @SerializedName("gistfile1.txt")
    @Expose
    private Gistfile1Txt gistfile1Txt;

    public Files(Gistfile1Txt gistfile1Txt) {
        this.gistfile1Txt = gistfile1Txt;
    }

    public Gistfile1Txt getGistfile1Txt() {
        return gistfile1Txt;
    }

    public void setGistfile1Txt(Gistfile1Txt gistfile1Txt) {
        this.gistfile1Txt = gistfile1Txt;
    }

}
