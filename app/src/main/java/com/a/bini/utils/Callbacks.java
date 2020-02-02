package com.a.bini.utils;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public class Callbacks {
    public interface VolleyCallback{
        void onSuccess(JSONObject response);
        void onError(VolleyError error);
    }
}
