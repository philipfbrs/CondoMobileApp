package com.example.condoapp.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.condoapp.R;
import com.example.condoapp.SharedViewModel;
import com.example.condoapp.controller.HomeController;
import com.example.condoapp.controller.MainController;
import com.example.condoapp.model.HomeModel;
import com.example.condoapp.model.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class HomeFragment extends Fragment {
    HomeModel home;
    String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVIjoiMmRjNzA1NzYtZTMxMC00YzlkLTgzYjgtNDZlZTM1N2Y1YjZiIiwiaWF0IjoxNjUyNTE0NDE3LCJleHAiOjE2NTI2MDA4MTd9.REqhrqs2UeZbxmFlO7DldQBJ5AOLF8VKSMsrLrwSdmc";
    ArrayList<Announcement> announcementArrayList;
    SharedViewModel viewModel;
    ListView lview;
    AnnouncementAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    String id = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String getPassedData[];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] s) {
                lview = (ListView) view.findViewById(R.id.announcement_listview);
                announcementArrayList = new ArrayList<>();
                adapter = new AnnouncementAdapter(getActivity(), 0, announcementArrayList);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                home = new HomeModel(s[0], getString(R.string.api_key), queue);
                home.announcementsData(new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            setupData(array);
                            setUpOnclickListener(s);
                            lview.setAdapter(adapter);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        System.out.println("making error test");
                    }



                });
            }
        });
        return view;
    }

    HomeController hc;

    private void setUpOnclickListener(String []s) {
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hc = new HomeController();
                Announcement adds = (Announcement) (lview.getItemAtPosition(i));
                Intent showDetail = new Intent(getActivity(), com.example.condoapp.activity.Announcement.class);
                showDetail.putExtra("announcement_title", adds.getTitle());
                showDetail.putExtra("announcement_message", adds.getMessage());
                showDetail.putExtra("announcement_date", adds.getCreatedAt());
                startActivity(hc.putAllDataPassed(showDetail,s));
                getActivity().finish();
            }
        });
    }


    private void setupData(JSONArray array) throws JSONException {
        announcementArrayList.clear();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = new JSONObject(array.getString(i));
                Announcement add = new Announcement(jsonObject.getString("id"), jsonObject.getString("title"), jsonObject.getString("message"), jsonObject.getString("createdAt"));
                announcementArrayList.add(add);
            }
            Collections.reverse(announcementArrayList);
        } catch (Exception e) {

        }

    }

//

}

class Announcement {
    String id, title, message, createdAt;

    public Announcement(String id, String title, String message, String createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

class AnnouncementAdapter extends ArrayAdapter<Announcement> {
    public AnnouncementAdapter(Context context, int resources, List<Announcement> announcementList) {
        super(context, resources, announcementList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Announcement add = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.announcement_log, parent, false);
        }
        TextView announcement_title = (TextView) convertView.findViewById(R.id.announcement_title);
        TextView announcement_created = (TextView) convertView.findViewById(R.id.announcement_date);

        announcement_title.setText(add.getTitle());
        announcement_created.setText(add.getCreatedAt().split("T")[0]);
        return convertView;
    }
}