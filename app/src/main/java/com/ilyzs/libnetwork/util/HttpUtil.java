package com.ilyzs.libnetwork.util;

import android.app.Activity;
import android.content.Context;

import com.ilyzs.libnetwork.AppBaseActivity;
import com.ilyzs.libnetwork.MainActivity;
import com.ilyzs.libnetwork.volley.VolleyHelper;

import java.util.List;

/**
 * 网络请求工具类
 * Created by zs
 */

public class HttpUtil {

    private static volatile HttpUtil httpUtil;

    public static HttpUtil getInstance(){
        synchronized (HttpUtil.class) {
            if (null == httpUtil) {
                httpUtil = new HttpUtil();
            }
        }
        return httpUtil;
    }

    public void doHttp(Context context, String urlKey, List<RequestParameter> parameter, RequestCallback callback){
        URLData urlData = URLDataManager.findURL(context,urlKey);
        ((AppBaseActivity)context).rmi.addRequest(urlData.getUrl());
        if("post".equals(urlData.getNetType())){
           doHttpPost(context,urlData.getUrl(),parameter,callback);
       }else{
           doHttpGet(context,urlData.getUrl(),parameter,callback);
       }
    }

    public void doHttpGet(Context context, String url, List<RequestParameter> parameter, RequestCallback callback){
        new VolleyHelper(context).doHttpGet(url,parameter,callback);
    }

    public  void doHttpPost(Context context,String url,List<RequestParameter> parameter, RequestCallback callback){
        new VolleyHelper(context).doHttpPost(url,parameter,callback);
    }

}
