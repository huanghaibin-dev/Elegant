package com.haibin.elegantproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.elegant.call.Call;
import com.haibin.elegant.call.CallBack;
import com.haibin.elegant.Elegant;
import com.haibin.elegant.Response;
import com.haibin.elegantproject.api.LoginService;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.User;

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

        elegant.from(LoginService.class).postAvatar("/storage/emulated/0/DCIM/Camera/1534724851.jpeg").execute(new CallBack<String>() {
            @Override
            public void onResponse(Response<String> response) {
                if (response != null) {
                    Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (e != null) {

                }
            }
        });
        elegant.from(LoginService.class).login("1@qq.com", "1", 2, 2).execute(new CallBack<BaseModel<User>>() {
            @Override
            public void onResponse(Response<BaseModel<User>> response) {
                if(response != null){

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
