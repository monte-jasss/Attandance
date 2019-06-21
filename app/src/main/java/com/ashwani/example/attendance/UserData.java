package com.ashwani.example.attendance;

import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class UserData {
    @SerializedName("user_id")
    private String user;

    @SerializedName("type")
    private int type;

    @SerializedName("msg")
    private String msg;

    public String getUser() {
        return user;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
