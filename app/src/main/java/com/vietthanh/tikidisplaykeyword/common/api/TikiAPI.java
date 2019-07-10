package com.vietthanh.tikidisplaykeyword.common.api;

import android.content.Context;
import android.util.Log;

import com.vietthanh.tikidisplaykeyword.common.util.Constant;
import com.vietthanh.tikidisplaykeyword.common.util.NetworkUtil;
import com.vietthanh.tikidisplaykeyword.R;
import com.vietthanh.tikidisplaykeyword.common.util.JacksonConverterUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TikiAPI {

    private APIInteract client;
    private Context mContext;
    private Retrofit mRetrofit;

    public TikiAPI() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("TikiAPI", message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    if (NetworkUtil.isNetworkConnected(mContext.getApplicationContext())) {
                        return chain.proceed(chain.request());
                    }

                    throw new NetworkException(mContext.getResources().getString(R.string.error_lost_network_connection));
                })
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseURL)
                .addConverterFactory(JacksonConverterFactory.create(JacksonConverterUtil.createObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        client = mRetrofit.create(APIInteract.class);
    }

    public APIInteract getApi(final Context context) {
        mContext = context;
        return client;
    }
}
