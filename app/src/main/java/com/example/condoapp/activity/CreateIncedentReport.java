package com.example.condoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.condoapp.MainActivity;
import com.example.condoapp.R;
import com.example.condoapp.controller.CreateIncidentReportController;

public class CreateIncedentReport extends AppCompatActivity {
    CreateIncidentReportController crc;
    String data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crc = new CreateIncidentReportController();
        data = new String[crc.getDataPassed().length];
        for (int i = 0; i < data.length; i++) {
            data[i] = getIntent().getExtras().getString(crc.getDataPassed()[i]);
        }
        setContentView(R.layout.activity_create_incedent_report);
    }
    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(crc.putAllDataPassed(i,data));
        finish();
    }

}