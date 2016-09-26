package com.haibin.elegant.factory;

import com.haibin.elegant.Elegant;
import com.haibin.elegant.call.CallBack;
import com.haibin.httpnet.core.Response;

import java.lang.reflect.ParameterizedType;

/**
 *
 */

public abstract class Convert<T> {
    public abstract void convert(Response response, Elegant elegant, CallBack<T> callBack, ParameterizedType returnType);
}
