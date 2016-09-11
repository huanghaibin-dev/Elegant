package com.haibin.elegantproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

//        elegant.from(LoginService.class).login("1@qq.com", "1", 2, 2).execute(new CallBack<BaseModel<User>>() {
//            @Override
//            public void onResponse(Response<BaseModel<User>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
        LoginService service = elegant.from(LoginService.class);

        Call<BaseModel<User>> call = service.login("1@qq.com", "1", 2, 2);

        call.execute(new CallBack<BaseModel<User>>() {
            @Override
            public void onResponse(Response<BaseModel<User>> response) {
                end = System.currentTimeMillis();
                textView.setText(response.getBodyString() +
                        "\n" + response.getBody() +
                        "\n" + response.getBody().getResult() +
                        "\n" + response.getBody().getMessage() +
                        "\n 共耗时：  " + (end - start) + "  毫秒");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        LoginService baiduService = elegant.from(LoginService.class);
        Call<String> baidu = service.getBaiDu();
        baidu.execute(new CallBack<String>() {
            @Override
            public void onResponse(Response<String> response) {
                textView.setText(response.getBodyString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
