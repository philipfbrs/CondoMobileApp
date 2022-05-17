package com.example.condoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.condoapp.MainActivity;
import com.example.condoapp.R;
import com.example.condoapp.controller.CreateIncidentReportController;
import com.example.condoapp.model.IncidentReportModel;
import com.example.condoapp.model.VolleyCallBack;

public class CreateIncedentReport extends AppCompatActivity {
    CreateIncidentReportController crc;
    IncidentReportModel irm;
    EditText title,message;
    RequestQueue queue;
    Button submit;
    String data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_incedent_report);
        crc = new CreateIncidentReportController();
        title = (EditText) findViewById(R.id.cinr_title);
        message = (EditText) findViewById(R.id.cinr_message);
        submit = (Button) findViewById(R.id.submit_btn_create_incident_report);
        data = new String[crc.getDataPassed().length];
        for (int i = 0; i < data.length; i++) {
            data[i] = getIntent().getExtras().getString(crc.getDataPassed()[i]);
        }
        queue = Volley.newRequestQueue(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(false);
                irm = new IncidentReportModel(getIntent().getExtras().getString("token"),getString(R.string.api_key),queue,title.getText().toString(),message.getText().toString());
                irm.createIncidentReport(new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        title.setText("");
                        message.setText("");
                        Toast.makeText(CreateIncedentReport.this, "Successfully created", Toast.LENGTH_SHORT).show();
                        submit.setEnabled(true);
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });
            }
        });


    }
    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(crc.putAllDataPassed(i,data));
        finish();
    }

}