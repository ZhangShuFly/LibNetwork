package com.ilyzs.libnetwork.util;

/**
 * Created by zs
 */

public interface RequestCallback {
    public void onSuccess(String content);

    public void onFail(String failMsg);
}
