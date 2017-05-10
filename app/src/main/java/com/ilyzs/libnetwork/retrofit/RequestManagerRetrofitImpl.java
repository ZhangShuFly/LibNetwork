package com.ilyzs.libnetwork.retrofit;

import android.support.v4.util.ArrayMap;

import com.ilyzs.libnetwork.util.RequestManagerInterface;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;

/**
 * 管理retrofit请求
 * Created by zs .
 */

public class RequestManagerRetrofitImpl implements RequestManagerInterface {

    private ArrayList requestList = null;
    private ArrayMap<String, Call> map = new ArrayMap<>();
    public RequestManagerRetrofitImpl(){
        this.requestList = new ArrayList();
    }

    public void addRequestQuneue(Call call){
        String url =call.request().url().toString();
        map.put(url,call);
        addRequest(url);
    }

    @Override
    public void addRequest(Object tag) {
        requestList.add(tag);
    }

    @Override
    public void cancelRequest(Object tag) {
            map.get(tag).cancel();
            map.remove(tag);
    }

    @Override
    public void cancelAllRequest() {
        for (Map.Entry<String,Call> entry : map.entrySet()){
            entry.getValue().cancel();
        }
        map.clear();
    }
}
