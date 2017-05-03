package com.ilyzs.libnetwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ilyzs.libnetwork.util.HttpUtil;
import com.ilyzs.libnetwork.util.RequestCallback;
import com.ilyzs.libnetwork.util.URLData;
import com.ilyzs.libnetwork.util.URLDataManager;
import com.ilyzs.libnetwork.volley.AppBaseActivity;


public class MainActivity extends AppBaseActivity {

    private Button volleyBtn;
    private TextView resultTv;

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
        volleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitDialogShow();
                HttpUtil.getInstance().doHttp(MainActivity.this, "getWeather", null, new RequestCallback() {
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
}
