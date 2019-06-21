package com.ashwani.example.attendance;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderzpassion on 13/03/16.
 */
public class MyCustomAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<StudentData> mylist=new ArrayList<>();
    public MyCustomAdapter(ArrayList<StudentData> itemArray,Context mContext) {
        super();
        this.mContext = mContext;
        mylist=itemArray;
    }
    @Override
    public int getCount() {
        return mylist.size();
    }
    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void onItemSelected(int position) {
    }
    public class ViewHolder {
        public TextView nametext;
        public CheckBox tick;
    }
    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view = null;
        LayoutInflater inflator = ((Activity) mContext).getLayoutInflater();
        if (view == null) {
            view = new ViewHolder();
            convertView = inflator.inflate(  R.layout.myadapter, null);
            view.nametext = (TextView) convertView.findViewById(R.id.adaptertextview);
            view.tick=(CheckBox)convertView.findViewById(R.id.adaptercheckbox);
            view.tick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    getStudent((Integer) compoundButton.getTag()).attendance = b;
                }
            });
//            view.tick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView,
//                                             boolean isChecked) {
//                    int getPosition = (Integer) buttonView.getTag(); // Here
//                    // we get  the position that we have set for the checkbox using setTag.
//                    mylist.get(getPosition).setChecked(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
//                    if (isChecked) {
//                        //do sometheing here
//                    }
//                    else
//                    {
//                        // code here
//                    }
//                }
//            });
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tick.setTag(position);
        view.nametext.setText("" + mylist.get(position).getEnrollment());
        view.tick.setChecked(false);
        return convertView;
    }

    ArrayList<StudentData> getMarkedAttendance() {
        ArrayList<StudentData> data = new ArrayList<StudentData>();
        for (StudentData p : mylist) {
            //if (p.getMarkAttendance())
                data.add(p);
        }
        return data;
    }

    StudentData getStudent(Integer tag) {
        return ((StudentData) getItem(tag));
    }
}