package com.rahul.git.Network;

import com.facebook.stetho.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Yogita Rachna on 2/18/2018.
 */

public class HttpLogger {

    public static Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.BASIC);
        return logger;
    }
}
