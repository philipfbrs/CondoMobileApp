package com.example.condoapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.condoapp.MainActivity;
import com.example.condoapp.R;
import com.example.condoapp.controller.AnnouncementController;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Announcement extends AppCompatActivity {
    TextView title_tv, message_tv, date_tv;
    Button back;
    AnnouncementController ac;
    Bundle extras;
    String getPassedData[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        try {
            ac = new AnnouncementController();
            extras = getIntent().getExtras();
            getPassedData = new String[ac.getDataPassed().length];
            for (int i = 0; i < ac.getDataPassed().length; i++) {
                getPassedData[i] = extras.getString(ac.getDataPassed()[i]);
            }
            title_tv = (TextView) findViewById(R.id.announcement_activity_header);
            message_tv = (TextView) findViewById(R.id.announcement_activity_message);
            date_tv = (TextView) findViewById(R.id.announcement_activity_date);
            back = (Button) findViewById(R.id.announcement_activity_back);
            title_tv.setText(extras.getString("announcement_title"));
            message_tv.setText(extras.getString("announcement_message"));
            date_tv.setText(extras.getString("announcement_date"));
        } catch (Exception e) {

        }
    }


    public void goBack(View view) {
        Intent i = new Intent(this,MainActivity.class);
        for (int j = 0; j < ac.getDataPassed().length; j++) {
            i.putExtra(ac.getDataPassed()[j],getPassedData[j]);
        }
        startActivity(i);
        finish();
    }
}