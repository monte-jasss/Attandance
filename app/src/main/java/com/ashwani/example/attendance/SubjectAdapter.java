package com.ashwani.example.attendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monte on 1/26/2018.
 */

public class SubjectAdapter extends ArrayAdapter {
    List<SubjectData> list = new ArrayList<SubjectData>();
    Manage data;

    public SubjectAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(@Nullable SubjectData object) {
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
            row = in.inflate(R.layout.card_layout, parent, false);

            data = new SubjectAdapter.Manage();
            data.t1 = row.findViewById(R.id.item_title);
            data.t2 = row.findViewById(R.id.item_detail);
            data.t3 = row.findViewById(R.id.item_credit);
            data.t4 = row.findViewById(R.id.item_id);
            row.setTag(data);
        } else data = (SubjectAdapter.Manage) row.getTag();

        data.t1.setText(list.get(position).getCode());
        data.t2.setText(list.get(position).getSubjectName());
        data.t3.setText(list.get(position).getCredit());
        data.t4.setText(list.get(position).getSubjectId());

        return row;
    }

    static class Manage{
        TextView t1, t2, t3, t4;
    }
}