## LibNetwork
Basic package of network communication.

## Instructions
We support volley, okhttp, and retrofit.The network request is realized by calling the HttpUtil.class method of dopost, and the framework is selected through the ConfigUtil setting.


#### 早期封装的一个网络请求框架，基于Volley、OkHttp、Retrofit.最近又给Retrofit加上了最新的RxJava

### 调用方法：

```
    /**
     * 网络请求入口
     * @param rmi 请求统一管理类
     * @param context  activity/fragment的引用
     * @param urlKey    获取url的参数
     * @param parameter  网络请求的参数
     * @param callback   请求结果的回调
     */
     doHttp(RequestManagerInterface rmi,Context context, String urlKey, List<RequestParameter> parameter, RequestCallback callback)

```
### 调用示例

```  
 HttpUtil.doHttp(rmi,MainActivity.this, "getWeatherRetrofit", parameterList, new RequestCallback() {
                     @Override
                     public void onSuccess(String content) {
                         waitDialogDismiss();
                         resultTv.setText(content);
                     }
 
                     @Override
                     public void onFail(String failMsg) {
                         waitDialogDismiss();
                         resultTv.setText(failMsg);
                     }
                 });
  ```
  