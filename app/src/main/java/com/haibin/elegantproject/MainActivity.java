package com.haibin.elegantproject;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haibin.elegant.Elegant;
import com.haibin.elegant.Response;
import com.haibin.elegant.call.Call;
import com.haibin.elegant.call.Callback;
import com.haibin.elegantproject.api.Service;
import com.haibin.elegantproject.model.BaseModel;
import com.haibin.elegantproject.model.User;
import com.haibin.httpnet.HttpNetClient;
import com.haibin.httpnet.builder.Headers;
import com.haibin.httpnet.builder.Request;
import com.haibin.httpnet.core.io.JsonContent;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    private Call mCallDownload;
    private Elegant mElegant;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private Request request;
    private HttpNetClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text1);
        mElegant = new Elegant();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        request = new Request.Builder()
                .url("http://api.mobileanjian.com/api/SetFeedBack")
                .content(new JsonContent("{\"AndoridData\":\"6.0.1\",\"BaseBandVersion\":\"MPSS.DI.4.0-20ac506\",\"CoreVersion\":\"3.4.0-gf4b741d-00639-ge918701\",\"DPI\":\"480\",\"Data\":{\"rdata\":[{\"Image\":\"0\"}]},\"FeedBack\":\"gegh\",\"GUID\":\"90845DEE-7577-48cf-A154-72E65CDE5586\",\"IMEI\":\"865931020628376\",\"Model\":\"MI 4LTE\",\"Process\":\"四核，CPU型号：ARMv7 Processor rev 1 (v7l) \",\"QQ\":\"5586586658\",\"RAM\":\"2.80 GB\",\"Resolution\":\"1080*1920\",\"RooStatus\":\"1\",\"SerialNumber\":\"5eeecb99\",\"SystemVersion\":\"MIUI系统\",\"TelNumber\":\"0\"}"))
                .method("POST")
                .build();
        client = new HttpNetClient();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_execute:
                //rxExecute();
                //rxDownload();
                //rxLogin();
                login();
//                for(int i=0;i<50;i++){
//                    jsonPost();
//                }
                break;
            case R.id.btn_cancel:
                if (mCallDownload != null)
                    mCallDownload.cancel();
                break;
        }
    }

    private void login(){
        new Elegant().from(Service.class)
                .text("huanghaibin_dev@163.com","R25ibhlC8WbVMBOLClxYVw==\n","2.5")
                .withHeaders(new Headers.Builder().addHeader("Cookie","12d4ed5917c9012ef9ca9974c57aa7fd;huanghaibin_dev@163.com"))
                .execute(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response) {
                        mTextView.setText(response.getBodyString());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        mTextView.setText(e.getMessage());
                    }
                });
    }

    private void jsonPost() {
        client.newCall(request)
                .execute(new com.haibin.httpnet.core.call.Callback() {
                    @Override
                    public void onResponse(com.haibin.httpnet.core.Response response) {
                        Log.e("res", "" + response.getBody());
                        jsonPost();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        jsonPost();
                    }
                });
    }

    /**
     * RxJava使用Elegant进行同步断点下载
     */
    private void rxDownload() {
        final File rangeFile = new File(Environment.getExternalStorageDirectory().getPath() + "/cnblogs.apk");
        final long readySize = rangeFile.exists() ? rangeFile.length() : 0;
        final Headers.Builder headers = new Headers.Builder()
                .addHeader("Range", "bytes=" + readySize + "-");
        Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(ObservableEmitter<Float> e) throws Exception {
                mCallDownload = mElegant.from(Service.class)
                        .download().withHeaders(headers);
                Response response = mCallDownload.execute();
                try {
                    if (response != null) {
                        InputStream is = response.toStream();
                        RandomAccessFile randomAccessFile = new RandomAccessFile(rangeFile, "rw");
                        randomAccessFile.seek(readySize);
                        long length = response.getContentLength();
                        length += readySize;
                        int p = (int) readySize;
                        int bytes;
                        byte[] buffer = new byte[1024];
                        while ((bytes = is.read(buffer)) != -1) {
                            randomAccessFile.write(buffer, 0, bytes);
                            p += bytes;
                            e.onNext((p / (float) length) * 100);
                        }
                        response.close();
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                    e.onError(error);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Float>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Float value) {
                        Log.e("Thread", "current thread" + Thread.currentThread().getName());
                        mTextView.setText("v" + value);
                        mProgressBar.setProgress(value.intValue());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("onError", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete", "onComplete");
                    }
                });
    }

    private void rxExecute() {
        Observable.create(new ObservableOnSubscribe<Response<String>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<String>> e) throws Exception {
                e.onNext(mElegant.from(Service.class)
                        .postJson("{\"input1\":\"fejf+TCnZ1omVx0pHYdzfmLZE9KIW2Td74mt37kvDcf2mkUnnw3DKCfV0Sy0ZYdWlVEv7YychSuuMsiv5vPvUSJtv94qBWmzOOhwq2GiBumYyKlddWps6VZ1WqiFXR0ng7mjIccEDI6QHfQDOl/sKyJFwxYe4GiVKN/Lv56nENs=\",\"input2\":\"FJVWSj2x6g85CK9jjTojx9nTxazEJpuN01pJnuL/d34h9HgsoKo5L9ypY2JsBPqO9GAoKD9bZ4bo7ujR8eDLMjv+ALTs8hq/C98Mfh5Grq81HgzpgdKW6z0zmywlws+XKR2ks9xGeqXAzBmgM6z/R/niYl6HITPXeihqHjJy/M8=\",\"remember\":false}")
                        .withHeaders(new Headers.Builder().addHeader("X-Requested-With", "XMLHttpRequest"))
                        .execute());
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<String> value) {
                        String s = value.getBodyString();
                        mTextView.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete", "onComplete");
                    }
                });
    }

    private void rxLogin() {
        Observable.create(new ObservableOnSubscribe<Response<BaseModel<User>>>() {
            @Override
            public void subscribe(ObservableEmitter<Response<BaseModel<User>>> e) throws Exception {
                Response<BaseModel<User>> res = mElegant.from(Service.class)
                        .login("1@qq.com", "1", 2, 2)
                        .execute();
                e.onNext(res);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseModel<User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<BaseModel<User>> value) {
                        mTextView.setText(value.getBodyString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError","onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete","onComplete");
                    }
                });
    }

    private void text() {

        mElegant.from(Service.class).login("1@qq.com", "1", 2, 2).execute(new Callback<BaseModel<User>>() {
            @Override
            public void onResponse(Response<BaseModel<User>> response) {
                if (response != null) {

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        mElegant.from(Service.class).postJson("{\"input1\":\"fejf+TCnZ1omVx0pHYdzfmLZE9KIW2Td74mt37kvDcf2mkUnnw3DKCfV0Sy0ZYdWlVEv7YychSuuMsiv5vPvUSJtv94qBWmzOOhwq2GiBumYyKlddWps6VZ1WqiFXR0ng7mjIccEDI6QHfQDOl/sKyJFwxYe4GiVKN/Lv56nENs=\",\"input2\":\"FJVWSj2x6g85CK9jjTojx9nTxazEJpuN01pJnuL/d34h9HgsoKo5L9ypY2JsBPqO9GAoKD9bZ4bo7ujR8eDLMjv+ALTs8hq/C98Mfh5Grq81HgzpgdKW6z0zmywlws+XKR2ks9xGeqXAzBmgM6z/R/niYl6HITPXeihqHjJy/M8=\",\"remember\":false}")
                .withHeaders(new Headers.Builder().addHeader("X-Requested-With", "XMLHttpRequest"))
                .execute(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response) {
                        if (response != null) {

                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        if (e != null) {

                        }
                    }
                });
    }
}
