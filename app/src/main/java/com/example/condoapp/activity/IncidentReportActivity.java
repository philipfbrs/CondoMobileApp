package com.example.condoapp.activity;

import com.example.condoapp.R;
import com.example.condoapp.controller.IncidentReportController;
import com.example.condoapp.fragment.IncidentReport.IncidentReport1Fragment;
import com.example.condoapp.fragment.IncidentReport.IncidentReportFragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class IncidentReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_report);
        IncidentReportController irc = new IncidentReportController();
        Bundle b = new Bundle();
        for (int i = 0; i < irc.getDataPassed().length; i++) {
            b.putString(irc.getDataPassed()[i],getIntent().getExtras().getString(irc.getDataPassed()[i]));
        }
        b.putString("irc_id",getIntent().getExtras().getString("irc_id"));
        b.putString("irc_title",getIntent().getExtras().getString("irc_title"));
        b.putString("irc_message",getIntent().getExtras().getString("irc_message"));
        b.putString("irc_date",getIntent().getExtras().getString("irc_date"));
        IncidentReport1Fragment irf = new IncidentReport1Fragment();
        irf.setArguments(b);
        irc.activity_to_fragment(-1,this,irf,null);
    }
}