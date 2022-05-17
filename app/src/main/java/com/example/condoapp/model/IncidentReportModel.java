package com.example.condoapp.model;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IncidentReportModel {
    String url3 = "https://condo-ms-api.herokuapp.com/v1/incidentReport/me";
    String token,key;
    RequestQueue queue;
    public IncidentReportModel(String token,String key,RequestQueue queue){
        this.token = "Bearer" + " " +token;
        this.key = key;
        this.queue = queue;
    }

    public void incidentReportData(final VolleyCallBack callBack) {
        try {
            StringRequest request = new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    callBack.onSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callBack.onError(error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("user-token", token);
                    params.put("x-api-key", key);

                    return params;
                }

            };
            queue.add(request);

        } catch (Exception e) {

        }
    }
}
