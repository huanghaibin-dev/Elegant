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


import com.haibin.elegant.Elegant;
import com.haibin.elegant.factory.GsonConvert;
import com.haibin.httpnet.builder.Headers;
import com.haibin.httpnet.builder.Request;
import com.haibin.httpnet.core.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 真正的请求
 *
 * @param <T>
 */
public class AsyncCall<T> implements Call<T> {
    private Elegant mElegant;
    private Request.Builder mBuilder;
    private ParameterizedType mReturnType;
    private Headers.Builder mHeaders;

    public AsyncCall(Elegant elegant, Request.Builder builder, Headers.Builder headers, Type mReturnType) {
        this.mElegant = elegant;
        this.mBuilder = builder;
        this.mHeaders = headers;
        this.mReturnType = (ParameterizedType) mReturnType;
    }

    @Override
    public Call<T> withHeaders(Headers.Builder headers) {
        if (mHeaders == null) mHeaders = new Headers.Builder();
        Map<String, List<String>> map = headers.build().getHeaders();
        Set<String> set = map.keySet();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            List<String> values = map.get(key);
            for (String h : values) {
                this.mHeaders.addHeader(key, h);
            }
        }
        mBuilder.headers(mHeaders);
        return this;
    }

    /**
     * 封装了HttpNet，在这里执行请求，并将返回json解析，切换到UI线程
     *
     * @param callBack
     */
    @Override
    public void execute(final CallBack<T> callBack) {
        if (mHeaders != null) {
            this.mBuilder.headers(mHeaders);
        }
        mElegant.getClient().newCall(mBuilder.build()).execute(new com.haibin.httpnet.core.call.CallBack() {
            @Override
            public void onResponse(Response response) {
                new GsonConvert<T>().convert(response, mElegant, callBack, mReturnType);
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
