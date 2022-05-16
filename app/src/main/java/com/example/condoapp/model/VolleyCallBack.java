package com.example.condoapp.model;

import com.android.volley.VolleyError;

public interface VolleyCallBack {

    void onSuccess(String response);
    void onError(VolleyError error);
}
