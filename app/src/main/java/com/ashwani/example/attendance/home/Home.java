package com.ashwani.example.attendance.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwani.example.attendance.Login.Login;
import com.ashwani.example.attendance.R;
import com.ashwani.example.attendance.RestClient;
import com.ashwani.example.attendance.ServerAPI;
import com.ashwani.example.attendance.SharedPreference;
import com.ashwani.example.attendance.SubjectAdapter;
import com.ashwani.example.attendance.SubjectData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    //RecyclerView recyclerView;
    //RecyclerView.LayoutManager layoutManager;
    //RecyclerView.Adapter adapter;
    private String id, email, name;
    TextView tv1,tv2;
    LinearLayout dr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dr = findViewById(R.id.temp);
        //tv1=(TextView) dr.findViewById(R.id.name);
        //tv2=(TextView) dr.findViewById(R.id.email);

        F_home fh = new F_home();

        id = getIntent().getExtras().get("id").toString();
        name = getIntent().getExtras().get("name").toString();
        email = getIntent().getExtras().get("email").toString();

       // tv1.setText(name);
       // tv2.setText(email);

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("name",name);
        fh.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fh, null);
        ft.commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        tv1.setText("monu");
//        tv2.setText("monu");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {

                   // case R.id.nav_tt:
                     //   new Handler().postDelayed(new Runnable() {
                           // @Override
                         //   public void run() {
                       //         drawer.closeDrawers();
                         //   }
                        //}, 50);
                       // getSupportActionBar().setTitle("Time Table");
                        // changeFragment(new F_timetable());
                        // item.setChecked(true);
                        // break;
                    case R.id.nav_develop:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        }, 50);
                        getSupportActionBar().setTitle("Developer");
                        changeFragment(new F_developer());
                        item.setChecked(true);
                        break;
                    case R.id.nav_sd:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        }, 50);
                        getSupportActionBar().setTitle("Details");
                        changeFragment(new F_details());
                        item.setChecked(true);
                        break;


                    case R.id.nav_logout:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                drawer.closeDrawers();
                            }
                        }, 50);
                        item.setChecked(true);
                        startActivity(new Intent(Home.this, Login.class));
                        finish();
                        break;



                    case R.id.nav_share:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.putExtra(Intent.EXTRA_SUBJECT, "SPSU_APP");
                        share.putExtra(Intent.EXTRA_TEXT, "This APP is Developed by Ashwani Lakshkar.");
                        startActivity(Intent.createChooser(share, "Share Via: "));
                        break;
                }
                return true;
            }
        });

    }

    private void changeFragment(Fragment f_home) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, f_home, null);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


   /* @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public void onBackPressed(){

        final AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
        builder.setMessage("Are you sure want to Logout");
        builder.setCancelable(true);
        builder.setNegativeButton("Close!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Home.this, Login.class));
                finish();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
         //   return true;
       // }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    /*@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        if (id == R.id.nav_home) {
            changeFragment(R.id.container,new F_home());

        }else if (id == R.id.nav_tt) {
            changeFragment(R.id.container, new F_timetable());

        }else if (id == R.id.nav_sd) {
            changeFragment(R.id.container,new F_details());

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

        }else if(id == R.id.nav_develop){
            changeFragment(R.id.container,new F_developer());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    private void changeFragment(int container, Fragment fm) {
        Toast.makeText(this, "" + fm, Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fm);
        fragmentTransaction.commit();


    }



}

