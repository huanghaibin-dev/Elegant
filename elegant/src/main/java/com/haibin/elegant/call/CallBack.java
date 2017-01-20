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

/**
 * 动态代理的回调
 */
public interface Callback<T> {
    /**
     * 请求成功的回调
     *
     * @param response 响应数据
     */
    void onResponse(Response<T> response);

    /**
     * 失败回调
     *
     * @param e 异常
     */
    void onFailure(Exception e);
}
