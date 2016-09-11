package com.haibin.elegantproject.api;

import com.haibin.elegant.call.Call;
import com.haibin.elegant.net.Form;
import com.haibin.elegant.net.GET;
import com.haibin.elegant.net.POST;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.User;


public interface LoginService {

    @POST("http://yunapi.xiejianji.com/api/Users/PostLogin")
    Call<BaseModel<User>> login(@Form("email") String email,
                                @Form("pwd") String pwd,
                                @Form("versionNum") int versionNum,
                                @Form("dataFrom") int dataFrom);

    @GET("https://www.baidu.com/")
    Call<String> getBaiDu();
}
