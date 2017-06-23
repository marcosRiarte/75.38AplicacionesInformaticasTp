package com.aplicacionesinformaticas.fiuba.requests;

/**
 * Created by Gregorio on 6/21/2017.
 */

public interface RequestCallback {
    public void onResponse(String response);
    public void onError(int statusCode);
}
