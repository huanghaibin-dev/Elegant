package com.haibin.elegantproject.api;

import com.haibin.elegant.call.Call;
import com.haibin.elegant.net.File;
import com.haibin.elegant.net.Form;
import com.haibin.elegant.net.Headers;
import com.haibin.elegant.net.POST;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.User;


public interface LoginService {

    @POST("http://xxx/api/Users/PostLogin")
    Call<BaseModel<User>> login(@Form("email") String email,
                                @Form("pwd") String pwd,
                                @Form("versionNum") int versionNum,
                                @Form("dataFrom") int dataFrom);

    @POST("http://www.oschina.net/action/apiv2/user_edit_portrait")
    @Headers("Cookie:xxx=hbbb;")
    Call<String> postAvatar(@File("portrait") String file);
}
