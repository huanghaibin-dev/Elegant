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

import com.haibin.elegant.Response;
import com.haibin.httpnet.builder.Headers;
import com.haibin.httpnet.core.call.InterceptListener;

import java.io.IOException;

/**
 * 请求的实现
 * Created by haibin
 * on 2017/1/18.
 */

public class AsyncCall1<T> implements Call<T> {
    @Override
    public Call<T> intercept(InterceptListener listener) {
        return null;
    }

    @Override
    public Call<T> withHeaders(Headers.Builder headers) {
        return null;
    }

    @Override
    public void execute(Callback<T> callback) {

    }

    /**
     * 同步执行，必须在子线程中执行
     */
    @Override
    public Response<T> execute() throws IOException {
        return null;
    }

    @Override
    public void cancel() {

    }
}
