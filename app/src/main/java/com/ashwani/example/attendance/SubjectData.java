package com.ashwani.example.attendance;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Monte on 1/27/2018.
 */

public class SubjectData {
    @SerializedName("subject_id")
    String subject_id;

    @SerializedName("code")
    String code;

    @SerializedName("name")
    String name;

    @SerializedName("credit")
    String credit;

    public String getSubjectId() {
        return subject_id;
    }

    public String getCode() {
        return code;
    }

    public String getSubjectName() {
        return name;
    }

    public String getCredit() {
        return credit;
    }
}
