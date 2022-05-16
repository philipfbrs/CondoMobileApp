package com.example.condoapp.controller;

import android.content.Intent;

public class CreateIncidentReportController {
    String [] dataPassed = {"token","id","first_name","last_name","email","condo_room_id","contact_no","role"};
    public String[] getDataPassed (){
        return  dataPassed;
    }
    public Intent putAllDataPassed(Intent i, String[]getPassedData){
        for (int j = 0; j < getDataPassed().length; j++) {
            i.putExtra(getDataPassed()[j],getPassedData[j]);
        }
        return i;
    }
}
