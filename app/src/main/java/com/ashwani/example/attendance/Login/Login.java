package com.ashwani.example.attendance.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ashwani.example.attendance.Admin.Admin;
import com.ashwani.example.attendance.R;
import com.ashwani.example.attendance.RestClient;
import com.ashwani.example.attendance.ServerAPI;
import com.ashwani.example.attendance.home.Home;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private Button b1;
    private EditText username,password;
    private ServerAPI serverAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.btn);

        serverAPI = RestClient.getRestClient().create(ServerAPI.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();
                if(Username.equals("")||Password.equals("")){
                    Toast.makeText(Login.this, "Enter all details !!", Toast.LENGTH_SHORT).show();
                } else {
                    if(isNetworkConnected()) {
                        performLogin(Username, Password);
                    } else {
                        Toast.makeText(Login.this, "No Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void performLogin(final String Username, String Password){


        Call<List<String>> call = serverAPI.authentication(Username,Password);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> data = response.body();
                Toast.makeText(Login.this, data.get(2), Toast.LENGTH_SHORT).show();
                if(data.get(2).equals("success")) {
                    if(Integer.parseInt(data.get(1)) == 1){
                        Intent i=new Intent(Login.this, Admin.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i=new Intent(Login.this, Home.class);
                        i.putExtra("id",data.get(0));
                        i.putExtra("name",data.get(3));
                        i.putExtra("email",Username);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(Login.this, "Login Failed, Try again !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
//        call.enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(Login.this, response.body(), Toast.LENGTH_SHORT).show();
//                if(response.body().equals("success")) {
//                    Intent i=new Intent(Login.this, Home.class);
//                    startActivity(i);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(Login.this, t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

}
