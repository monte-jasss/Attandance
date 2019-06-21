package com.ashwani.example.attendance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Sub_adapter extends ArrayAdapter {

    private List list=new ArrayList();

    public Sub_adapter(@NonNull Context context, int resource) {
        super(context, resource);

    }

    public void add(@Nullable SubjectModel object) {
        list.add(object);
        super.add(object);
    }

    static class DataHolder{

        TextView name,code;

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
        row= convertView;
        DataHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.card_layout, parent, false);
            holder = new DataHolder();
            holder.name = (TextView) row.findViewById(R.id.item_detail);
            holder.code = (TextView) row.findViewById(R.id.item_title);
            row.setTag(holder);
        }
        else{

            holder=(DataHolder) row.getTag();

        }


        SubjectModel sm= (SubjectModel) getItem(position);
        holder.name.setText(sm.getName());
        holder.code.setText(sm.getCode());

        return row;
    }
}
