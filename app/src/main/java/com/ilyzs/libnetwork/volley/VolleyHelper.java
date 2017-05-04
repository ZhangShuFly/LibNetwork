package com.ilyzs.libnetwork.volley;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestParameter;

import org.json.JSONObject;

import java.util.List;

/**
 * volley帮助类
 * Created by zs .
 */

public class VolleyHelper {
    private Context context;

    public VolleyHelper(Context context) {
        this.context = context;
    }

    public void doHttpGet(String url, List<RequestParameter> rpList, final RequestCallback callback) {
        Response.Listener successLister = getSuccessListener(callback);
        Response.ErrorListener errorListener = getErrorListener(callback);
        JsonObjectRequestVolley jorv = new JsonObjectRequestVolley(url, parseParameter(rpList), successLister, errorListener);
        Volley.newRequestQueue(context).add(jorv);
    }

    public void doHttpPost(String url, List<RequestParameter> rpList, final RequestCallback callback) {
        Response.Listener successLister = getSuccessListener(callback);
        Response.ErrorListener errorListener = getErrorListener(callback);
        JsonObjectRequestVolley jorv = new JsonObjectRequestVolley(Request.Method.POST,url, parseParameter(rpList), successLister, errorListener);
        Volley.newRequestQueue(context).add(jorv);
    }

    @NonNull
    private Response.ErrorListener getErrorListener(final RequestCallback callback) {
        return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(null!=callback)
                    callback.onFail(error.getMessage());
                }
            };
    }

    @NonNull
    private Response.Listener getSuccessListener(final RequestCallback callback) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(null!=callback)
                callback.onSuccess(response.toString());
            }
        };
    }
    private JSONObject parseParameter(List<RequestParameter> rpList) {
        JSONObject jo = new JSONObject();
        if(null!=rpList){
            for (RequestParameter rp : rpList) {

            }
        }
        return jo;
    }
}
