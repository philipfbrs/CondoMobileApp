package com.example.condoapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.condoapp.MainActivity;
import com.example.condoapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginController {
    String [] dataPassed = {"id","first_name","last_name","email","condo_room_id","contact_no","role"};
    public void activity_to_fragment (int i,FragmentActivity getClass, Fragment lf, JSONObject array){
        try {
            Bundle bundle = new Bundle();
            if(i == 0){
                bundle.putString("token",array.getString("accessToken"));
            }
            lf.setArguments(bundle);
            FragmentManager manager = getClass.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.container_login, lf);
            fragmentTransaction.addToBackStack(null).commit();
        }catch (Exception e){

        }

    }

    public String[] getDataPassed (){
        return  dataPassed;
    }
}
