package com.example.condoapp.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.condoapp.R;

import org.json.JSONObject;

public class IncidentReportController {
    String [] dataPassed = {"token","id","first_name","last_name","email","condo_room_id","contact_no","role"};
    public void activity_to_fragment (int i, FragmentActivity getClass, Fragment lf, JSONObject array){
        try {
            FragmentManager manager = getClass.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.incident_report_container, lf);
            fragmentTransaction.addToBackStack(null).commit();
        }catch (Exception e){

        }

    }
    public Intent putAllDataPassed(Intent i, String[]getPassedData){
        for (int j = 0; j < getDataPassed().length; j++) {
            i.putExtra(getDataPassed()[j],getPassedData[j]);
        }
        return i;
    }
    public String[] getDataPassed (){
        return  dataPassed;
    }
}
