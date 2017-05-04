package com.ilyzs.libnetwork;

import android.os.Bundle;

import com.ilyzs.libbase.BaseActivity;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.volley.RequestManagerVolley;

/**
 * Created by zs .
 */

public abstract class AppBaseActivity extends BaseActivity {

    public RequestManagerInterface rmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rmi = new RequestManagerVolley(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        rmi.cancleAllRequest();
    }
}
