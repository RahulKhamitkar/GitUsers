package com.rahul.git.Network;

import com.facebook.stetho.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * Created by Yogita Rachna on 2/18/2018.
 */

public class OkHttpClientBuilderFactory {
    public OkHttpClient.Builder getNewOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }
        return okHttpClientBuilder;
    }
}
