package com.example.condoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.condoapp.R;
import com.example.condoapp.controller.LoginController;
import com.example.condoapp.fragment.login.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    LoginController l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        l = new LoginController();
        LoginFragment lf = new LoginFragment();
        l.activity_to_fragment(-1,this,lf,null);
    }
}