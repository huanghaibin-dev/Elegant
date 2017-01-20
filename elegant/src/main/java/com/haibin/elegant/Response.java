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
package com.haibin.elegant;

import com.haibin.httpnet.core.io.IO;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 返回信息
 *
 * @param <T>
 */
@SuppressWarnings("unused")
public class Response<T> {
    private Map<String, List<String>> mHeaders;
    private InputStream mInputStream;
    private int mCode;
    private String mBodyString;
    private T mBody;
    private long mContentLength;

    public String getBodyString() {
        return mBodyString;
    }

    public void setBodyString(String mBodyString) {
        this.mBodyString = mBodyString;
    }

    public T getBody() {
        return mBody;
    }

    public void setBody(T body) {
        this.mBody = body;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public Map<String, List<String>> getHeaders() {
        return mHeaders;
    }

    public void setHeaders(Map<String, List<String>> mHeaders) {
        this.mHeaders = mHeaders;
    }

    public InputStream toStream() {
        return mInputStream;
    }

    public void setInputStream(InputStream mInputStream) {
        this.mInputStream = mInputStream;
    }

    public long getContentLength() {
        return mContentLength;
    }

    public void setContentLength(long mContentLength) {
        this.mContentLength = mContentLength;
    }

    public void close() {
        IO.close(mInputStream);
    }
}
