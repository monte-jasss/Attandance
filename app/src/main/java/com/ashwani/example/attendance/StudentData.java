package com.ashwani.example.attendance;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Monte on 1/27/2018.
 */

public class StudentData {
    @SerializedName("subject_id")
    String subject_id;

    @SerializedName("student_id")
    int student_id;

    @SerializedName("enrollment")
    String enrollment;

    @SerializedName("name")
    String name;

    @SerializedName("semester")
    String semester;

    @SerializedName("attendance")
    boolean attendance;

    public StudentData(String subject_id, int student_id, String enrollment, String name, String semester, boolean attendance) {
        this.subject_id = subject_id;
        this.student_id = student_id;
        this.enrollment = enrollment;
        this.name = name;
        this.semester = semester;
        this.attendance = attendance;
    }

    public int getStudentId() {
        return student_id;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public boolean getMarkAttendance() {
        return attendance;
    }
}
