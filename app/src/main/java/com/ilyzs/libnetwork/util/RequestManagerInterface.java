package com.ilyzs.libnetwork.util;

import com.android.volley.Request;

/**
 * 管理请求的接口，用于取消请求
 * Created by zs.
 */

public interface RequestManagerInterface {

    public void  addRequest(Object tag);
    public void  cancelRequest(Object tag);
    public void  cancelAllRequest();
}
