package com.ashwani.example.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetails extends AppCompatActivity {
    private ServerAPI serverAPI;
    TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        t1 = findViewById(R.id.text8);
        t2 = findViewById(R.id.text9);
        t3 = findViewById(R.id.text10);
        //t4 = findViewById(R.id.text11);
        //t5 = findViewById(R.id.text18);
        serverAPI = RestClient.getRestClient().create(ServerAPI.class);
        String id = getIntent().getExtras().get("id").toString();
        Call<List<StudentData>> call = serverAPI.showStudent(id);
        call.enqueue(new Callback<List<StudentData>>() {
            @Override
            public void onResponse(Call<List<StudentData>> call, Response<List<StudentData>> response) {
                List<StudentData> data = response.body();
                Toast.makeText(ShowDetails.this, ""+data.get(0).getEnrollment(), Toast.LENGTH_SHORT).show();
                t1.setText(data.get(0).getEnrollment());
                t2.setText(data.get(0).getName());
                t3.setText(data.get(0).getSemester());
            }

            @Override
            public void onFailure(Call<List<StudentData>> call, Throwable t) {

            }
        });
    }
}
