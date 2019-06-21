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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwani.example.attendance.R;
import com.ashwani.example.attendance.RestClient;
import com.ashwani.example.attendance.Select;
import com.ashwani.example.attendance.ServerAPI;
import com.ashwani.example.attendance.Sub_adapter;
import com.ashwani.example.attendance.SubjectAdapter;
import com.ashwani.example.attendance.SubjectData;
import com.ashwani.example.attendance.SubjectModel;
import com.ashwani.example.attendance.home.Home;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class F_home extends Fragment {

    private ServerAPI serverAPI;
    SharedPreferences sharedPreference;
    ListView listView;
    SubjectAdapter subjectAdapter;

    public F_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_f_home, container, false);

        listView = v.findViewById(R.id.list);
        subjectAdapter = new SubjectAdapter(getContext(),R.layout.card_layout);
        listView.setAdapter(subjectAdapter);

        Bundle bundle = getArguments();
        String userId = bundle.getString("id");

        Toast.makeText(getContext(), "id : "+userId, Toast.LENGTH_SHORT).show();

        serverAPI = RestClient.getRestClient().create(ServerAPI.class);

        if(isNetworkConnected()) {
            Call<List<SubjectData>> call = serverAPI.getSubject(userId);
            call.enqueue(new Callback<List<SubjectData>>() {
                @Override
                public void onResponse(Call<List<SubjectData>> call, Response<List<SubjectData>> response) {
                    subjectAdapter.clear();
                    List<SubjectData> data = response.body();
                    for (SubjectData item : data) {
                        subjectAdapter.add(item);
                    }
                }

                @Override
                public void onFailure(Call<List<SubjectData>> call, Throwable t) {

                }
            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.item_id);
                Home h=(Home) getActivity();
                Intent i = new Intent(h, Select.class);
                i.putExtra("subID", t.getText().toString());
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
