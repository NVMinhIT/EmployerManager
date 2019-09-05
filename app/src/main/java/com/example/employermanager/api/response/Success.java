package com.example.employermanager.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success {
    @SerializedName("text")
    @Expose
    private String text;

    public Success(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
