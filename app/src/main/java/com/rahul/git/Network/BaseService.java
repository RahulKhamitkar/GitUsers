package com.rahul.git.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Yogita Rachna on 2/18/2018.
 */

public abstract class BaseService<T> {

    public T newTransaction() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClientBuilderFactory().getNewOkHttpClient()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        Interceptor logger = HttpLogger.getLoggingInterceptor();
        if (logger != null) {
            okHttpClientBuilder.addInterceptor(logger);
        }

        return getService(okHttpClientBuilder.build());
    }

    public abstract String getBaseApiUrl();

    abstract protected T getService(OkHttpClient okHttpClient);
}
