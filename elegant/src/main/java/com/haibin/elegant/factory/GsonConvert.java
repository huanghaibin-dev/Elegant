package com.haibin.elegant.factory;

import com.google.gson.Gson;
import com.haibin.elegant.Elegant;
import com.haibin.elegant.call.CallBack;
import com.haibin.httpnet.core.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 */

public class GsonConvert<T> extends Convert<T> {
    @Override
    public void convert(Response response, Elegant elegant, final CallBack<T> callBack, ParameterizedType returnType) {
        final com.haibin.elegant.Response res = new com.haibin.elegant.Response();
        res.setBodyString(response.getBody());
        res.setCode(response.getCode());
        res.setHeaders(response.getHeaders());
        try {
            Type[] types = returnType.getActualTypeArguments();
            res.setBody(new Gson().fromJson(res.getBodyString(), types[0]));
            elegant.getMainExecutor().runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    callBack.onResponse(res);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            elegant.getMainExecutor().runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    callBack.onResponse(res);
                }
            });
        }
    }
}
