package com.example.condoapp.fragment.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.condoapp.MainActivity;
import com.example.condoapp.R;
import com.example.condoapp.controller.LoginController;
import com.example.condoapp.model.LoginModel;
import com.example.condoapp.model.VolleyCallBack;

import org.json.JSONObject;


public class WelcomeFragment extends Fragment {



    public WelcomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    LoginController l;
    CountDownTimer x;
    LoginModel lm;
    RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        String token = getArguments().getString("token");
        l = new LoginController();
        x = new CountDownTimer(3000, 1000) {


            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                x.cancel();
                queue = Volley.newRequestQueue(getActivity());
                lm = new LoginModel(token,getString(R.string.api_key),queue,null,null);
                lm.getUserProfile(new VolleyCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        try{
                            Intent i = new Intent(getActivity(),MainActivity.class);
                            i.putExtra("token",token);
                            JSONObject object =new JSONObject(response);
                            for (int j = 0; j < l.getDataPassed().length; j++) {
                                i.putExtra(l.getDataPassed()[j],object.getString(l.getDataPassed()[j])+"");
                            }
                            startActivity(i);
                            getActivity().finish();
                        }catch (Exception e){

                        }

                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });

            }
        }.start();

        return view;
    }
}