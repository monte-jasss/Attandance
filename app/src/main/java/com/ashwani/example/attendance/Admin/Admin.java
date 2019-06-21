package com.ashwani.example.attendance.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import com.ashwani.example.attendance.Login.Login;
import com.ashwani.example.attendance.R;

public class Admin extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        toolbar=(Toolbar)findViewById(R.id.ad_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin");

        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.pager);

        adapter=new ViewPageAdapter(getSupportFragmentManager());
       // adapter.addFragments(new U_Stdlist(),"Student List");
        // adapter.addFragments(new U_timetble(),"Time Table");
       // adapter.addFragments(new U_tlist(),"Teacher List");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onBackPressed(){

        final AlertDialog.Builder builder=new AlertDialog.Builder(Admin.this);
        builder.setMessage("Are you sure want to Logout");
        builder.setCancelable(true);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Admin.this, Login.class));
                finish();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



}
