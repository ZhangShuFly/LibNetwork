package com.ilyzs.libnetwork.retrofit;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestManagerInterface;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.util.URLData;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zs.
 */

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private Retrofit retrofit;
    private static String baseUrl = "";
    private RetrofitHelper(String url){
        if(url.length()<7){
            return;
        }
        baseUrl = url.substring(0,url.indexOf("/",7)+1);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    private static RetrofitHelper getInstance(final  String url){
        synchronized (RetrofitHelper.class){
            if(null==retrofitHelper || !url.contains(baseUrl)){
                retrofitHelper = new RetrofitHelper(url);
            }
        }
        return retrofitHelper;
    }

    public static void doHttp(RequestManagerInterface rmi, URLData urlData, List<RequestParameter> rpList, RequestCallback callback) {
        String url = urlData.getUrl();
        RetrofitHelper instance =  getInstance(url);
        if(null!=instance)
            instance.inner_doHttp(rmi,urlData.getKey(),rpList,callback);
    }


    private void inner_doHttp(RequestManagerInterface rmi, String urlKey, List<RequestParameter> rpList, final RequestCallback callback) {
        String methodName = urlKey;
        RetrofitUrlApi urlApi = retrofit.create(RetrofitUrlApi.class);
        try {
            Method method = urlApi.getClass().getMethod(methodName,new Class[]{Map.class});
            Call<ResponseBody> call =  (Call<ResponseBody>) method.invoke(urlApi,parseParameter(rpList));
            call.enqueue(getReponseCallback(callback));

            RequestManagerRetrofitImpl rmoi = (RequestManagerRetrofitImpl)rmi;
            rmoi.addRequestQuneue(call);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private Callback<ResponseBody> getReponseCallback(final RequestCallback callback) {
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(null!=callback)
                    try {
                        callback.onSuccess(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(null!=callback)
                    callback.onFail(t.getMessage());
            }
        };
    }

    private Map<String,Object> parseParameter(List<RequestParameter> rpList) {
        Map map = new HashMap();
        if(null!=rpList && !rpList.isEmpty()){
            for (RequestParameter rp : rpList) {
                if(null!=rp.getValue() && null!=rp.getName()){
                    map.put(rp.getName(),rp.getValue());
                }
            }
        }
        return map;
    }
}
