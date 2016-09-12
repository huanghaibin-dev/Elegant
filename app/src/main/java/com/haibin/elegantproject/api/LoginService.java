package com.haibin.elegantproject.api;

import com.haibin.elegant.call.Call;
import com.haibin.elegant.net.File;
import com.haibin.elegant.net.Form;
import com.haibin.elegant.net.Headers;
import com.haibin.elegant.net.POST;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.User;


public interface LoginService {

    @POST("http://yunapi.xiejianji.com/api/Users/PostLogin")
    Call<BaseModel<User>> login(@Form("email") String email,
                                @Form("pwd") String pwd,
                                @Form("versionNum") int versionNum,
                                @Form("dataFrom") int dataFrom);

    @POST("http://www.oschina.net/action/apiv2/user_edit_portrait")
    @Headers("Cookie:_user_behavior_=f75ecb6d-1892-484a-a6d6-686eb8ba4c9d;oscid=ZASbNu5yuK5DBpbDDnOIGO%2F3nF0RhRCf7T8vnDBZS7VddIIO4a%2FcUXXrhr37jP7XXw1jGPrKEK9tt6VJNUUbNITRopfiSTlpR7YykiDjkEJmZoD2y%2F8kPamFy8Rl7V71WSf7jd1FmYsTjOEJ62qV8esIy9RU4DtH;")
    Call<String> postAvatar(@File("portrait") String file);
}
