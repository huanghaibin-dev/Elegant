package com.haibin.elegantproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haibin.elegant.Elegant;
import com.haibin.elegant.Response;
import com.haibin.elegant.call.CallBack;
import com.haibin.elegantproject.api.Service;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.PageBean;
import com.haibin.elegantproject.model.ResultBean;
import com.haibin.elegantproject.model.Tweet;
import com.haibin.elegantproject.model.User;
import com.haibin.httpnet.builder.Headers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        elegant.from(Service.class)
                .getTweet(1, "")
                .execute(new CallBack<ResultBean<PageBean<Tweet>>>() {
                    @Override
                    public void onResponse(Response<ResultBean<PageBean<Tweet>>> response) {
                        for (Tweet tweet : response.getBody().getResult().getItems()) {

                        }
                        Tweet tweet = response.getBody().getResult().getItems().get(16);
                        SpannableStringBuilder builder = new SpannableStringBuilder(tweet.getContent());
                        Pattern pattern = Pattern.compile(
                                "<a\\s+href=['\"]http[s]?://my\\.oschina\\.[a-z]+/(\\w+|u/([0-9]+))['\"][^<>]*>(@([^@<>\\s]+))</a>");
                        Matcher matcher = pattern.matcher(builder.toString());
                        while (matcher.find()) {
                            final String group0 = matcher.group(1); // ident
                            final String group1 = matcher.group(2); // uid
                            final String group2 = matcher.group(3); // @Nick
                            final String group3 = matcher.group(4); // Nick
                            int start = matcher.start();
                            int end = matcher.end();
                            int lenght = matcher.group().length();
                            builder.replace(matcher.start(), matcher.end(), group2);
                            long uid = 0;
                            try {
                                uid = group1 == null ? 0 : Integer.valueOf(group1);
                            } catch (Exception e) {
                                uid = 0;
                            }
                            final long _uid = uid;
                            ClickableSpan span = new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
                                    if (_uid > 0) {
                                        Log.e("1", "1");
                                    } else if (!TextUtils.isEmpty(group0)) {
                                        Log.e("2", "2");
                                    } else {
                                        Log.e("3", "3");
                                    }
                                }
                            };
                            builder.setSpan(span, matcher.start(), matcher.start() + group2.length(),
                                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            textView.setText(builder);
                            textView.setClickable(true);
                            textView.setLongClickable(true);
                            textView.setFocusable(false);
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }

    public void onClick(View view) {
        start = System.currentTimeMillis();

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
