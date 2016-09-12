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
package com.haibin.elegant.factory;

import com.haibin.elegant.call.Call;
import com.haibin.elegant.Elegant;
import com.haibin.elegant.call.AsyncCall;
import com.haibin.httpnet.builder.Request;

import java.lang.reflect.Type;

/**
 * 请求工厂，用于将代理转换为真正的请求
 * @param <T>
 */
public class RequestFactory<T> {
    private Type mReturnType;
    private Request.Builder mBuilder;

    public RequestFactory(Type returnType, Request.Builder builder) {
        this.mReturnType = returnType;
        this.mBuilder = builder;
    }

    /**
     * 转换为真正的请求
     * @param elegant 代理
     * @return 返回对象
     */
    public Call<T> convert(Elegant elegant) {
        return new AsyncCall<>(elegant, mBuilder, mReturnType);
    }
}
