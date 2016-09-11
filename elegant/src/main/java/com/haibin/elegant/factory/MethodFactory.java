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
import com.haibin.elegant.net.Encode;
import com.haibin.elegant.net.File;
import com.haibin.elegant.net.Form;
import com.haibin.elegant.net.GET;
import com.haibin.elegant.net.Json;
import com.haibin.elegant.net.POST;
import com.haibin.httpnet.builder.Request;
import com.haibin.httpnet.builder.RequestParams;
import com.haibin.httpnet.core.io.JsonContent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 方法解析类，解析方法返回类型与注解信息
 * parse the method Annotation and return type
 */
public class MethodFactory {
    private Elegant mElegant;
    private Request.Builder mBuilder;
    private RequestParams mParams;
    private Method mMethod;
    private Type mReturnType;

    public MethodFactory(Elegant mElegant) {
        this.mElegant = mElegant;
    }

    public Object invoke(Object... args) {
        parseMethodParamsAnnotation(mMethod.getParameterAnnotations(), args);
        return new RequestFactory(mReturnType, mBuilder.params(mParams).build()).convert(mElegant);
    }

    public MethodFactory from(Method method) {
        this.mMethod = method;
        mReturnType = method.getGenericReturnType();
        if (mReturnType instanceof Class<?>)
            return null;
        if (mReturnType instanceof ParameterizedType) {
            mBuilder = new Request.Builder();
            parseMethodAnnotation(method.getAnnotations());
        }
        return this;
    }

    /**
     * 获取并解析方法注解
     * @param annotations 方法注解
     */
    private void parseMethodAnnotation(Annotation[] annotations) {
        if (annotations != null) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof POST) {
                    mBuilder.method("POST");
                    mBuilder.url(((POST) annotation).value());
                } else if (annotation instanceof GET) {
                    mBuilder.method("GET");
                    mBuilder.url(((GET) annotation).value());
                } else if (annotation instanceof Encode) {
                    mBuilder.encode(((Encode) annotation).value());
                }
            }
        }
    }

    /**
     * 获取并解析方法参数的注解
     * @param annotations 参数注解
     * @param args 实际参数
     */
    private void parseMethodParamsAnnotation(Annotation[][] annotations, Object[] args) {
        if (annotations != null) {
            mParams = new RequestParams();
            for (int i = 0; i < annotations.length; i++) {
                Annotation annotation = annotations[i][0];
                if (annotation instanceof Form) {
                    Form form = (Form) annotation;
                    mParams.put(form.value(), args[i].toString());
                } else if (annotation instanceof File) {
                    File file = (File) annotation;
                    mParams.putFile(file.value(), args[i].toString());
                } else if (annotation instanceof Json) {
                    mBuilder.content(new JsonContent(((Json) annotation).value()));
                }
            }
        }
    }
}
