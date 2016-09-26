package com.haibin.elegantproject.api;

import com.haibin.elegant.call.Call;
import com.haibin.elegant.net.File;
import com.haibin.elegant.net.Form;
import com.haibin.elegant.net.GET;
import com.haibin.elegant.net.Headers;
import com.haibin.elegant.net.Json;
import com.haibin.elegant.net.POST;
import com.haibin.elegant.net.Proxy;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.PageBean;
import com.haibin.elegantproject.model.ResultBean;
import com.haibin.elegantproject.model.Tweet;
import com.haibin.elegantproject.model.User;


public interface Service {

    @Proxy(host = "192.168.1.1", port = 80)
    @POST("http://xxx/api/Users/PostLogin")
    Call<BaseModel<User>> login(@Form("email") String email,
                                @Form("pwd") String pwd,
                                @Form("versionNum") int versionNum,
                                @Form("dataFrom") int dataFrom);

    @GET("http://www.oschina.net/action/apiv2/user_info")
    @Headers("Cookie:_user_behavior_=196d5ba7-4fd5-420e-a142-732920ad3eba;oscid=ZASbNu5yuK5DBpbDDnOIGO%2F3nF0RhRCf7T8vnDBZS7VddIIO4a%2FcUXXrhr37jP7XyNVRHPOuVUpjU%2FfxsPp1MUYmrJy8EY3HE7jWcp8gE6s0R8%2BLR99s%2FqmFy8Rl7V71WSf7jd1FmYsTjOEJ62qV8esIy9RU4DtH;")
    Call<String> postAvatar();

    @POST("https://passport.cnblogs.com/user/signin")
    @Headers("Cookie:_ga=GA1.2.2128538109.1473746167; SERVERID=d0849c852e6ab8cf0cebe3fa386ea513|1474879648|1474879647")
    Call<String> postJson(@Json String json);

    @GET("http://www.cnblogs.com/")
    Call<String> getBlog();

    @GET("https://www.oschina.net/action/apiv2/tweets")
    Call<ResultBean<PageBean<Tweet>>> getTweet(@Form("type") int type,
                                               @Form("pageToken") String token);
}
