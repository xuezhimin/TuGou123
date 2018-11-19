package com.umeng.soexample.net;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {

    private static volatile HttpUtils instance;
    private final OkHttpClient client;

    private Handler handler = new Handler();



    public static HttpUtils getInstance(){
        if (instance==null) {
            synchronized (HttpUtils.class){
                if (null == instance) {
                    instance = new HttpUtils();
                }
            }
        }


        return instance;
    }

    private HttpUtils(){
        client = new OkHttpClient();
    }

    public void get(String url, final ICallBack callBack, final Type type){
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       callBack.failed(e);
                   }
               });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(o);
                    }
                });

            }
        });

    }


}
