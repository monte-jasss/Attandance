package com.ashwani.example.attendance.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwani.example.attendance.MyCustomAdapter;
import com.ashwani.example.attendance.R;
import com.ashwani.example.attendance.RestClient;
import com.ashwani.example.attendance.Select;
import com.ashwani.example.attendance.ServerAPI;
import com.ashwani.example.attendance.ShowDetails;
import com.ashwani.example.attendance.StudentData;
import com.ashwani.example.attendance.SubjectAdapter;
import com.ashwani.example.attendance.SubjectData;
import com.ashwani.example.attendance.stlistAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class F_details extends Fragment {

    private ServerAPI serverAPI;
    SharedPreferences sharedPreference;
    ListView listView;
    stlistAdapter stlistAdapter;
    ArrayList<StudentData> myitems = new ArrayList<>();

    public F_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_f_details, container, false);

        listView = v.findViewById(R.id.Stlist);
        stlistAdapter = new stlistAdapter(myitems,getContext());
        listView.setAdapter(stlistAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = view.findViewById(R.id.id);
                Intent im = new Intent(getActivity(), ShowDetails.class);
                im.putExtra("id", t.getText().toString());
                startActivity(im);
            }
        });

        //Bundle bundle = getArguments();
        //String userId = bundle.getString("id");

        //Toast.makeText(getContext(), "id : "+userId, Toast.LENGTH_SHORT).show();

        serverAPI = RestClient.getRestClient().create(ServerAPI.class);

        if(isNetworkConnected()) {
            Call<List<StudentData>> call = serverAPI.getAllStudent();
            call.enqueue(new Callback<List<StudentData>>() {
                @Override
                public void onResponse(Call<List<StudentData>> call, Response<List<StudentData>> response) {
                    //MyCustomAdapter.clear();
                    List<StudentData> data = response.body();
                    for (StudentData item : data) {
                        myitems.add(item);
                        stlistAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<StudentData>> call, Throwable t) {

                }
            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.id);
                Intent i = new Intent(getActivity(), ShowDetails.class);
                i.putExtra("id", t.getText().toString());
                startActivity(i);
            }
        });

        return v;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


}
