package com.ilyzs.libnetwork.util;

/**
 * 管理请求的接口，用于取消请求
 * Created by zs.
 */

public interface RequestManagerInterface {

    public void  addRequest(Object tag);
    public void  cancleRequest(Object tag);
    public void  cancleAllRequest();
}
