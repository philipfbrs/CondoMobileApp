package com.example.condoapp.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.condoapp.R;
import com.example.condoapp.SharedViewModel;
import com.example.condoapp.model.IncidentReportModel;
import com.example.condoapp.model.VolleyCallBack;
import com.simform.refresh.SSPullToRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class IncidentReportFragment extends Fragment {



    public IncidentReportFragment() {
        // Required empty public constructor
    }
    IncidentReportAdapter adapter;
    ArrayList<IncidentReport> incidentReportArrayList;
    SSPullToRefreshLayout ssPullToRefreshLayout;
    SharedViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    IncidentReportModel irm;
    ListView incident_report_lv;
    RequestQueue queue;
    CountDownTimer refresh_timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_incident_report, container, false);
        queue = Volley.newRequestQueue(getActivity());
        ssPullToRefreshLayout = view.findViewById(R.id.ssRefreshIncident);
        incident_report_lv = (ListView) view.findViewById(R.id.incident_report_lv);
        incidentReportArrayList = new ArrayList<>();
        adapter = new IncidentReportAdapter(getActivity(), 0, incidentReportArrayList);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] s) {
                refreshData(s);
                adapter.notifyDataSetChanged();
                incident_report_lv.setAdapter(adapter);
                ssPullToRefreshLayout.setOnRefreshListener(new SSPullToRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refresh_timer = new CountDownTimer(4000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                try {
                                    refreshData(s);
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFinish() {
                                refresh_timer.cancel();
                                ssPullToRefreshLayout.setRefreshing(false);
                            }
                        };
                        refresh_timer.start();
                    }
                });
            }
        });
        return view;
    }
    public void refreshData(String []s){
        irm = new IncidentReportModel(s[0],getString(R.string.api_key),queue);
        irm.incidentReportData(new VolleyCallBack() {
            @Override
            public void onSuccess(String response) {
                try{
                    JSONArray object = new JSONArray(response);
                    setupData(object);
                }catch (Exception e){

                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
    private void setupData(JSONArray array) throws JSONException {
        incidentReportArrayList.clear();
        try {
            System.out.println(array);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = new JSONObject(array.getString(i));
                IncidentReport add = new IncidentReport(jsonObject.getString("id"), jsonObject.getString("title"), jsonObject.getString("message"), jsonObject.getString("status"),jsonObject.getString("createdAt"),jsonObject.getString("updatedAt"));
                incidentReportArrayList.add(add);
            }
            System.out.println(incidentReportArrayList.size());
            Collections.reverse(incidentReportArrayList);
            adapter.notifyDataSetChanged();
            refresh_timer.cancel();
            ssPullToRefreshLayout.setRefreshing(false);
        } catch (Exception e) {

        }

    }
}

class IncidentReport {
    public IncidentReport(String id, String title, String message, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    String id,title,message,status,createdAt,updatedAt;
}
class IncidentReportAdapter extends ArrayAdapter<IncidentReport> {
    public IncidentReportAdapter(Context context, int resources, List<IncidentReport> incidentReportList) {
        super(context, resources, incidentReportList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IncidentReport add = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.announcement_log, parent, false);
        }
        TextView announcement_title = (TextView) convertView.findViewById(R.id.announcement_title);
        TextView announcement_created = (TextView) convertView.findViewById(R.id.announcement_date);

        announcement_title.setText(add.getTitle());
        announcement_created.setText(add.getStatus());
        if(add.getStatus().equalsIgnoreCase("ACTIVE")){
            announcement_created.setTextColor(Color.GREEN);
        }else{
            announcement_created.setTextColor(Color.RED);
        }
        return convertView;
    }
}