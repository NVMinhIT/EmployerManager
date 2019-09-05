package com.example.employermanager.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteResult {
    @SerializedName("success")
    @Expose
    private Success success;

    public DeleteResult() {

    }

    public DeleteResult(Success success) {
        this.success = success;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }
}
