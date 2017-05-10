package com.ilyzs.libnetwork.retrofit;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zs .
 */

public interface RetrofitUrlApi {

    @GET("/weather_mini")
    Call<ResponseBody> getWeatherRetrofit(@QueryMap Map<String, Object> map);
}
