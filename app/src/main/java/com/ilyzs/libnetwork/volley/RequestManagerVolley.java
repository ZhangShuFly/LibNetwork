package com.ilyzs.libnetwork.volley;

import android.content.Context;

import com.android.volley.toolbox.Volley;
import com.ilyzs.libnetwork.util.RequestManagerInterface;

import java.util.ArrayList;

/**
 *管理volley请求的实现，取消请求
 * Created by  zs
 */

public class RequestManagerVolley implements RequestManagerInterface {
    private ArrayList requestList = null;
    private Context context;
    public RequestManagerVolley(Context context){
        requestList = new ArrayList();
        this.context = context;
    }

    public void  addRequest(Object tag){
        requestList.add(tag);
    }

    public void cancleRequest(Object tag){
        Volley.newRequestQueue(context).cancelAll(tag);
    }

    public  void cancleAllRequest(){
        for (Object tag:requestList) {
            Volley.newRequestQueue(context).cancelAll(tag);
        }
    }
}
