package com.ashwani.example.attendance;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ashwani.example.attendance.home.Home;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_list extends AppCompatActivity {

    Button btn1;
    ListView mainactivity;
    ServerAPI serverAPI;
    StudentAdapter studentAdapter;
    ArrayList<StudentData> myitems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        mainactivity = (ListView) findViewById(R.id.mainactivitylistview);
        //Creating Adapter object for setting to list
        final MyCustomAdapter adapter = new MyCustomAdapter(myitems, Student_list.this);


        btn1 = (Button) findViewById(R.id.subbtn);

        final String subID = getIntent().getExtras().get("subID").toString();
        final String time = getIntent().getExtras().get("time").toString();
        final String date = getIntent().getExtras().get("date").toString();
        final String type = getIntent().getExtras().get("type").toString();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (StudentData p : adapter.getMarkedAttendance()) {
                    final String x = String.valueOf(p.getStudentId());
                    String y;
                    if(p.getMarkAttendance()) y="1";
                    else y="0";
                    if (isNetworkConnected()) {
                        Call<List<String>> call = serverAPI.markAttendance(x,subID,time,date,type,y);
                        call.enqueue(new Callback<List<String>>() {
                            @Override
                            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                                List<String> d = response.body();
                                Toast.makeText(Student_list.this, d.get(0), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<List<String>> call, Throwable throwable) {
                                Toast.makeText(Student_list.this, x+"] error = "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                /*Intent intent = new Intent(Student_list.this, Home.class);
                startActivity(intent);
                finish();*/

            }
        });


        serverAPI = RestClient.getRestClient().create(ServerAPI.class);

        if (isNetworkConnected()) {
            Call<List<StudentData>> call = serverAPI.getStudent(subID);
            call.enqueue(new Callback<List<StudentData>>() {
                @Override
                public void onResponse(Call<List<StudentData>> call, Response<List<StudentData>> response) {
                    //studentAdapter.clear();
                    List<StudentData> data = response.body();
                    for (StudentData item : data) {
                        myitems.add(item);
                        Toast.makeText(Student_list.this, item.getEnrollment() + "=>" + item.getName(), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<StudentData>> call, Throwable throwable) {

                }
            });
        }

        mainactivity.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);  //this shows three dots at right corner on click settings open
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
          //  return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}




