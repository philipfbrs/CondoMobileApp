package com.example.condoapp.fragment.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.condoapp.R;
import com.example.condoapp.controller.LoginController;
import com.example.condoapp.model.LoginModel;
import com.example.condoapp.model.VolleyCallBack;
import com.example.kloadingspin.KLoadingSpin;

import org.json.JSONArray;
import org.json.JSONObject;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    LoginController l;
    LoginModel lm;
    Button login;
    EditText username,password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        login = (Button) view.findViewById(R.id.login_btn);
        KLoadingSpin a = view.findViewById(R.id.KLoadingSpin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.startAnimation();
                a.setIsVisible(true);
                login.setEnabled(false);
                l = new LoginController();
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                lm = new LoginModel(null,getString(R.string.api_key),queue,username.getText().toString(),password.getText().toString());
                lm.loginData(new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONObject array = new JSONObject(response);
                            WelcomeFragment wf = new WelcomeFragment();
                            l.activity_to_fragment(0,getActivity(),wf,array);
                            login.setEnabled(true);
                            a.stopAnimation();
                        }catch (Exception e){
                            Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                            login.setEnabled(true);
                            a.stopAnimation();
                        }
                    }
                    @Override
                    public void onError(VolleyError error) {
                        login.setEnabled(true);
                        Toast.makeText(getActivity(), "Login Failed: Invalid Username & Password", Toast.LENGTH_SHORT).show();
                        a.stopAnimation();
                    }
                });


            }
        });

        return view;
    }
}