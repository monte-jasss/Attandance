package com.ashwani.example.attendance;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Monte on 1/27/2018.
 */

public interface ServerAPI {
    @FormUrlEncoded
    @POST("login.php")
    Call<List<String>> authentication(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("show-subject.php")
    Call<List<SubjectData>> getSubject(@Field("id") String id);

    @FormUrlEncoded
    @POST("show-student.php")
    Call<List<StudentData>> getStudent(@Field("id") String id);

    @FormUrlEncoded
    @POST("student-detail.php")
    Call<List<StudentData>> showStudent(@Field("id") String id);

    @POST("all-student.php")
    Call<List<StudentData>> getAllStudent();

    @FormUrlEncoded
    @POST("mark-attendance.php")
    Call<List<String>> markAttendance(@Field("std_id")String std_id,@Field("sub_id")String sub_id,
                                @Field("time")String time,@Field("date")String date,@Field("type")String type,@Field("attendance")String attendance);
/*
    @FormUrlEncoded
    @POST("time-table.php")
    Call<ResponseBody> getTimeTable(@Field("semester") String semester, @Field("branch") String Branch);

    @POST("semester.php")
    Call<List<String>> getSemester();

    @POST("branch.php")
    Call<List<String>> getBranch();*/
}