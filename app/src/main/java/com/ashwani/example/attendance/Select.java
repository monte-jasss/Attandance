package com.ashwani.example.attendance;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ashwani.example.attendance.home.Home;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Select extends AppCompatActivity {

    private static final String TAG = "Select";
    private TextView mDisplayDate, textView;
    private DatePickerDialog.OnDateSetListener mDateSetListner;


    Button b1, b2;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mDisplayDate = (TextView) findViewById(R.id.date);
        textView = (TextView) findViewById(R.id.time);
        spinner = (Spinner) findViewById(R.id.spiner);


        //Spinner......
        List<String> list = new ArrayList<String>();
        list.add("Select Any One");
        list.add("Lecture");
        list.add("Practical");
        list.add("Tutorial");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Date....
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Select.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListner,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                ;
            }
        });

        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);

            }
        };


        Button b1 = (Button) findViewById(R.id.cancle);
        Button b2 = (Button) findViewById(R.id.submit);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Select.this, Home.class);
                startActivity(i);
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDisplayDate.getText().equals("Select Date")&&textView.getText().equals("Select Time")){
                    Toast.makeText(Select.this, "Select all fields !!", Toast.LENGTH_SHORT).show();
                } else {

                    Intent i = new Intent(Select.this, Student_list.class);
                    i.putExtra("subID", getIntent().getExtras().get("subID").toString());
                    i.putExtra("date", mDisplayDate.getText());
                    i.putExtra("time", textView.getText());
                    i.putExtra("type", spinner.getSelectedItem().toString());
                    Toast.makeText(Select.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();
                }
            }
        });

    }


    //Time.....
    public void setTime(View view) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(Select.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                textView.setText(hourOfDay + ":" + minute);

            }
        }, hour, min, true);
        timePickerDialog.show();


    }


}
