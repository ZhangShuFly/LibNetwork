package com.ilyzs.libnetwork;

import android.os.Bundle;

import com.ilyzs.libbase.BaseActivity;
import com.ilyzs.libnetwork.okHttp.RequestManagerOkHttpImpl;
import com.ilyzs.libnetwork.util.ConfigUtil;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.volley.RequestManagerVolleyImpl;

/**
 * Created by zs .
 */

public abstract class AppBaseActivity extends BaseActivity {

    public RequestManagerInterface rmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if("OKHttp".equals(ConfigUtil.netType)){
            rmi = new RequestManagerOkHttpImpl();
        }else{
            rmi = new RequestManagerVolleyImpl(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        rmi.cancelAllRequest();
    }
}
