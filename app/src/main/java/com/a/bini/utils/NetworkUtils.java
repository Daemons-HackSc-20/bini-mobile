package com.a.bini.utils;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    private static final String DEFAULT_HOST = "http://35.236.71.139/api/v1";

    public static void fetchRandomQuestion(Context context, final Callbacks.VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Uri.Builder uri = Uri.parse(DEFAULT_HOST).buildUpon().appendPath("play");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });

        queue.add(request);
    }

    public static void submitAnswer(String answer,String objectId, Context context, final Callbacks.VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Uri.Builder uri = Uri.parse(DEFAULT_HOST).buildUpon().appendPath("play");

        Map<String, String> params = new HashMap<>();
        params.put("answer", answer);
        params.put("objectId", objectId);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, uri.toString(), new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });

        queue.add(request);
    }
}
