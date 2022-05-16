package com.example.condoapp.fragment.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.condoapp.R;

import java.util.List;


public class IncidentReportFragment extends Fragment {



    public IncidentReportFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_incident_report, container, false);
        return view;
    }
}

//
//class Transaction {
//    String type_of_service = "";
//    String date_service;
//    String trans_id = "";
//    String free_id = "";
//    String client_id = "";
//    String time = "";
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    String price = "";
//
//    public String getType_of_service() {
//        return type_of_service;
//    }
//
//    public void setType_of_service(String type_of_service) {
//        this.type_of_service = type_of_service;
//    }
//
//    public String getDate_service() {
//        return date_service;
//    }
//
//    public void setDate_service(String date_service) {
//        this.date_service = date_service;
//    }
//
//    public String getTrans_id() {
//        return trans_id;
//    }
//
//    public void setTrans_id(String trans_id) {
//        this.trans_id = trans_id;
//    }
//
//    public String getFree_id() {
//        return free_id;
//    }
//
//    public void setFree_id(String free_id) {
//        this.free_id = free_id;
//    }
//
//    public String getClient_id() {
//        return client_id;
//    }
//
//    public void setClient_id(String client_id) {
//        this.client_id = client_id;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public Transaction(String type_of_service, String date_service, String trans_id, String free_id, String client_id, String time,String price) {
//        this.type_of_service = type_of_service;
//        this.date_service = date_service;
//        this.trans_id = trans_id;
//        this.free_id = free_id;
//        this.client_id = client_id;
//        this.time = time;
//        this.price = price;
//    }
//
//
//}
//
//class add_historyAdapter extends ArrayAdapter<Transaction> {
//    public add_historyAdapter(Context context, int resources, List<Transaction> transactionList) {
//        super(context, resources, transactionList);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Transaction add = getItem(position);
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trans_history_item, parent, false);
//        }
////        TextView tv = (TextView) convertView.findViewById(R.id.type_of_service);
////        TextView tv1 = (TextView) convertView.findViewById(R.id.date_time);
////        TextView tv2 = (TextView) convertView.findViewById(R.id.trans_id);
////        TextView tv3 = (TextView) convertView.findViewById(R.id.price_textview);
////        tv.setText(add.getType_of_service());
////        tv1.setText(add.getDate_service() + ", " + add.getTime());
////        tv2.setText("Trans ID+ "+add.getTrans_id());
////        tv3.setText("₱ "+add.getPrice());
//        return convertView;
//    }
//}