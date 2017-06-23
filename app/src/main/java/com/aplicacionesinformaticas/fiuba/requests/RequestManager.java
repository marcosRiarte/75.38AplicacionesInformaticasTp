package com.aplicacionesinformaticas.fiuba.requests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Gregorio on 6/21/2017.
 */

public class RequestManager{
    private RequestManager instance;
    private Context context;

    private RequestManager() {

    }

    public RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager();
        }

        return instance;
    }




    public void doRequest(int method, String url, Context context, final RequestCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        callback.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.networkResponse.statusCode);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void deGetRequest(String url, Context context, final RequestCallback callback) {
        doRequest(Request.Method.GET, url, context, callback);

    }

    public void doPostRequest(String url, Context context, final RequestCallback callback) {
        doRequest(Request.Method.POST, url, context, callback);
    }

    public void doPatchRequest(String url, Context context, final RequestCallback callback) {
        doRequest(Request.Method.PATCH, url, context, callback);
    }
}
