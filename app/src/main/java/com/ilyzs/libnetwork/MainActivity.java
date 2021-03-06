package com.ilyzs.libnetwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ilyzs.libnetwork.okHttp.RequestManagerOkHttpImpl;
import com.ilyzs.libnetwork.util.ConfigUtil;
import com.ilyzs.libnetwork.util.HttpUtil;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.RequestParameter;
import com.ilyzs.libnetwork.volley.RequestManagerVolleyImpl;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppBaseActivity {

    private Button volleyBtn,okHttpBtn,retrofitBtn;
    private TextView resultTv;
    private List parameterList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initPara() {

    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_main);
        resultTv = (TextView) findViewById(R.id.main_result_tv);
        volleyBtn = (Button) findViewById(R.id.main_volley_test_btn);
        okHttpBtn = (Button)findViewById(R.id.main_okHttp_test_btn);
        retrofitBtn = (Button)findViewById(R.id.main_retrofit_test_btn);

        volleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNetType("Volley");
                waitDialogShow();
                HttpUtil.doHttp(rmi,MainActivity.this, "getWeather", null, new RequestCallback() {
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
            }
        });

        okHttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNetType("OKHttp");
                waitDialogShow();
                HttpUtil.doHttp(rmi,MainActivity.this, "sh601006", null, new RequestCallback() {
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
            }
        });

        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNetType("Retrofit");
                waitDialogShow();

                RequestParameter parameter = new RequestParameter();
                parameter.setName("citykey");
                parameter.setValue("101010100");
                parameterList.add(parameter);

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
            }
        });

    }


    @Override
    public void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
