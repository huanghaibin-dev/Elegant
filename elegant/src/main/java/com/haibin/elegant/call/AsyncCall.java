/*
 * Copyright (C) 2016 huanghaibin_dev <huanghaibin_dev@163.com>
 * WebSite https://github.com/MiracleTimes-Dev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haibin.elegant.call;


import com.google.gson.Gson;
import com.haibin.elegant.Elegant;
import com.haibin.httpnet.builder.Headers;
import com.haibin.httpnet.builder.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 真正的请求
 *
 * @param <T>
 */
public class AsyncCall<T> implements Call<T> {
    private Elegant mElegant;
    private Request.Builder mBuilder;
    private ParameterizedType mReturnType;

    public AsyncCall(Elegant elegant, Request.Builder builder, Type mReturnType) {
        this.mElegant = elegant;
        this.mBuilder = builder;
        this.mReturnType = (ParameterizedType) mReturnType;
    }

    public AsyncCall withHeaders(Headers.Builder headers) {
        mBuilder.headers(headers);
        return this;
    }

    /**
     * 封装了HttpNet，在这里执行请求，并将返回json解析，切换到UI线程
     *
     * @param callBack
     */
    @Override
    public void execute(final CallBack<T> callBack) {
        mElegant.getClient().newCall(mBuilder.build()).execute(new com.haibin.httpnet.core.call.CallBack() {
            @Override
            public void onResponse(com.haibin.httpnet.core.Response response) {
                final com.haibin.elegant.Response res = new com.haibin.elegant.Response();
                res.setBodyString(response.getBody());
                res.setCode(response.getCode());
                res.setHeaders(response.getHeaders());
                try {
                    Type[] types = mReturnType.getActualTypeArguments();
                    res.setBody(new Gson().fromJson(res.getBodyString(), types[0]));
                    mElegant.getMainExecutor().runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onResponse(res);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    mElegant.getMainExecutor().runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onResponse(res);
                        }
                    });
                }
            }

            @Override
            public void onFailure(final Exception e) {
                mElegant.getMainExecutor().runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }
        });
    }

    @Override
    public void cancel() {

    }
}
