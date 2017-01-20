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

import com.haibin.elegant.Elegant;
import com.haibin.elegant.Response;
import com.haibin.elegant.call.Callback;

import java.lang.reflect.ParameterizedType;

/**
 * 转换器
 */

abstract class Convert<T> {
    boolean isToStream;

    Convert(boolean isToStream) {
        this.isToStream = isToStream;
    }

    /**
     * 异步转换响应
     *
     * @param response   返回数据
     * @param elegant    代理客户端
     * @param callback   回调
     * @param returnType 代理类型
     */
    public abstract void convert(com.haibin.httpnet.core.Response response, Elegant elegant, Callback<T> callback, ParameterizedType returnType);

    /**
     * 同步转换响应
     *
     * @param response   返回数据
     * @param elegant    代理客户端
     * @param returnType 代理类型
     * @return 代理转换的数据
     */
    public abstract Response<T> convert(com.haibin.httpnet.core.Response response, Elegant elegant, ParameterizedType returnType);
}
