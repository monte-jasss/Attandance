package com.ashwani.example.attendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monte on 1/26/2018.
 */

public class StudentAdapter extends ArrayAdapter {
    List<StudentData> list = new ArrayList<StudentData>();
    Manage data;

    public StudentAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(@Nullable StudentData object) {
        super.add(object);
        this.list.add(object);
    }

    @Override
    public void clear() {
        super.clear();
        this.list.clear();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        if(row==null){
            LayoutInflater in = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = in.inflate(R.layout.myadapter, null);

            data = new StudentAdapter.Manage();
            data.t1 = row.findViewById(R.id.adaptertextview);
            data.t2 = row.findViewById(R.id.adaptercheckbox);
            data.t2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    getStuden((Integer) compoundButton.getTag()).attendance = b;
                }
            });
            row.setTag(data);
        } else data = (StudentAdapter.Manage) row.getTag();

        data.t1.setText(list.get(position).getEnrollment());
        data.t2.setChecked(false);

        return row;
    }

    StudentData getStuden(Integer tag) {
        return ((StudentData) getItem(tag));
    }

    List<StudentData> getMarkedAttendance() {
        List<StudentData> data = new ArrayList<StudentData>();
        for (StudentData p : list) {
            if (p.getMarkAttendance())
                data.add(p);
        }
        return data;
    }

    static class Manage{
        TextView t1;
        CheckBox t2;
    }
}