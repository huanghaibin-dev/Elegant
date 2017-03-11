#Elegant项目结构如下
![输入图片说明](http://git.oschina.net/uploads/images/2016/0912/100446_970746ab_494015.png "在这里输入图片标题")

Elegant采用Retrofit动态代理+构建的思想，本身并不做网络请求，网络部分基于[HttpNet](https://github.com/huanghaibin-dev/HttpNet)实现，本着简洁清晰的思想，保持了和Retrofit相似的API

##gradle
```
compile 'com.haibin:elegant:1.1.9'
```

##创建API接口
```java
public interface LoginService {
     
    //普通POST
    @Headers({"Cookie:cid=adcdefg;"})
    @POST("api/users/login")
    Call<BaseModel<User>> login(@Form("email") String email,
                                @Form("pwd") String pwd,
                                @Form("versionNum") int versionNum,
                                @Form("dataFrom") int dataFrom);

    // 上传文件                           
    @POST("action/apiv2/user_edit_portrait")
    @Headers("Cookie:xxx=hbbb;")
    Call<String> postAvatar(@File("portrait") String file);

    
    //JSON POST
    @POST("action/apiv2/user_edit_portrait")
    @Headers("Cookie:xxx=hbbb;")
    Call<String> postJson(@Json String file);
    
    //PATCH
    @PATCH("mobile/user/{uid}/online")
    Call<ResultBean<String>> handUp(@Path("uid") long uid);
}
```

##执行请求
```java
public static final String API = "http://www.oschina.net/";
public static Elegant elegant = new Elegant();

static {
    elegant.registerApi(API);
}

LoginService service = elegant.from(LoginService.class)
                              .login("xxx@qq.com", "123456", 2, 2);
                              .withHeaders(Headers...)
                              .execute(new CallBack<BaseModel<User>>() {
                                      @Override
                                      public void onResponse(Response<BaseModel<User>> response) {
                
                                      }

                                      @Override
                                      public void onFailure(Exception e) {

                                      }
                              });
```


##Licenses
- Copyright (C) 2013 huanghaibin_dev <huanghaibin_dev@163.com>
 
- Licensed under the Apache License, Version 2.0 (the "License");
- you may not use this file except in compliance with the License.
- You may obtain a copy of the License at
 
-         http://www.apache.org/licenses/LICENSE-2.0
 
- Unless required by applicable law or agreed to in writing, software
- distributed under the License is distributed on an "AS IS" BASIS,
- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
- See the License for the specific language governing permissions and
  limitations under the License.
 
