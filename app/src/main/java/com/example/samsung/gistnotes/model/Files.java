package com.example.samsung.gistnotes.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by btow on 27.03.2017.
 */

class Files {
//    @SerializedName("gistfile1.txt")
//    public-gists.txt GistTypeFile.gistfile1txt _$Gistfile1Txt275; // FIXME check this code

    public static Files objectFromData(String str) {

        return new Gson().fromJson(str, Files.class);
    }

    public static Files objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Files.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Files> arrayFilesBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<Files>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Files> arrayFilesBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Files>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

//    public-gists.txt Gistfile1.txtBean get_$Gistfile1Txt275() {
//        return _$Gistfile1Txt275;
//    }
//
//    public-gists.txt void set_$Gistfile1Txt275(GistTypeFile.gistfile1txt _$Gistfile1Txt275) {
//        this._$Gistfile1Txt275 = _$Gistfile1Txt275;
//    }
}
