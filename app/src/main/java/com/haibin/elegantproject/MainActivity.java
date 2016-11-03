package com.haibin.elegantproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haibin.elegant.Elegant;
import com.haibin.elegant.Response;
import com.haibin.elegant.call.CallBack;
import com.haibin.elegantproject.api.Service;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.User;
import com.haibin.httpnet.builder.Headers;

public class MainActivity extends AppCompatActivity {

    private Elegant elegant;
    private TextView textView;
    private long start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text1);
        elegant = new Elegant();
    }

    public void onClick(View view) {
        start = System.currentTimeMillis();
        elegant.registerApi("http://www.cnblog.com/");
        elegant.from(Service.class)
                .getBlog("123", "345")
                .execute(new CallBack<String>() {
                    @Override
                    public void onResponse(Response<String> response) {
                        Log.e("aaa", response.getBodyString());
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });

        elegant.from(Service.class).postAvatar().execute(new CallBack<String>() {
            @Override
            public void onResponse(Response<String> response) {
                if (response != null) {
                    Log.e("response", response.getBodyString());
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (e != null) {

                }
            }
        });
        elegant.from(Service.class).login("1@qq.com", "1", 2, 2).execute(new CallBack<BaseModel<User>>() {
            @Override
            public void onResponse(Response<BaseModel<User>> response) {
                if (response != null) {

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        elegant.from(Service.class).postJson("{\"input1\":\"fejf+TCnZ1omVx0pHYdzfmLZE9KIW2Td74mt37kvDcf2mkUnnw3DKCfV0Sy0ZYdWlVEv7YychSuuMsiv5vPvUSJtv94qBWmzOOhwq2GiBumYyKlddWps6VZ1WqiFXR0ng7mjIccEDI6QHfQDOl/sKyJFwxYe4GiVKN/Lv56nENs=\",\"input2\":\"FJVWSj2x6g85CK9jjTojx9nTxazEJpuN01pJnuL/d34h9HgsoKo5L9ypY2JsBPqO9GAoKD9bZ4bo7ujR8eDLMjv+ALTs8hq/C98Mfh5Grq81HgzpgdKW6z0zmywlws+XKR2ks9xGeqXAzBmgM6z/R/niYl6HITPXeihqHjJy/M8=\",\"remember\":false}")
                .withHeaders(new Headers.Builder().addHeader("X-Requested-With", "XMLHttpRequest"))
                .execute(new CallBack<String>() {
                    @Override
                    public void onResponse(Response<String> response) {
                        if (response != null) {

                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        if (e != null) {

                        }
                    }
                });
    }
}
