package com.ilyzs.libnetwork.util;

/**
 * 请求结果回调接口
 * Created by zs
 */

public interface RequestCallback {
    public void onSuccess(String content);

    public void onFail(String failMsg);
}
