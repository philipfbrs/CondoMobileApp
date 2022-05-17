package com.example.condoapp.fragment.IncidentReport;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.condoapp.MainActivity;
import com.example.condoapp.R;
import com.example.condoapp.controller.IncidentReportController;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;


public class IncidentReport1Fragment extends Fragment {

    public IncidentReport1Fragment() {
        // Required empty public constructor
    }
    ExpandableRelativeLayout relativeLayout;
    LinearLayout headerLinear;
    TextView name,role,date,title,message;
    ImageButton back;
    IncidentReportController irc;
    String passedData [];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_incident_report_1_, container, false);
        irc = new IncidentReportController();
        passedData = new String[irc.getDataPassed().length];
        for (int i = 0; i < irc.getDataPassed().length; i++) {
            passedData[i] = getArguments().getString(irc.getDataPassed()[i]);
        }
        relativeLayout = (ExpandableRelativeLayout) view.findViewById(R.id.expandable_relative_layout_incident_report);
        headerLinear = (LinearLayout) view.findViewById(R.id.headerLinear);
        name = (TextView) view.findViewById(R.id.incident_report_name_1);
        role = (TextView) view.findViewById(R.id.incident_report_role_1);
        date = (TextView) view.findViewById(R.id.incident_report_date_1);
        title = (TextView) view.findViewById(R.id.incident_report_title_1);
        message = (TextView) view.findViewById(R.id.incident_report_message_1);
        back = (ImageButton) view.findViewById(R.id.back_btn_incident_report_1);
        name.setText(getArguments().getString("first_name") + " " + getArguments().getString("last_name"));
        role.setText(getArguments().getString("role"));
        date.setText(getArguments().getString("irc_date"));
        title.setText(getArguments().getString("irc_title"));
        message.setText(getArguments().getString("irc_message"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        headerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.toggle();
            }
        });
        return view;
    }
    public void goBack(){
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(irc.putAllDataPassed(i,passedData));
        getActivity().finish();
    }
}