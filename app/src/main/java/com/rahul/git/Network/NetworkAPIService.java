package com.rahul.git.Network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rahul.git.Model.UserDetailsPojo;
import com.rahul.git.Model.UsersPojo;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Yogita Rachna on 2/18/2018.
 */

public class NetworkAPIService extends BaseService<NetworkAPIService.DeviceApi>  {

    public final String BASE_API_URL ="https://api.github.com/";


    private static NetworkAPIService deviceService;

    public static synchronized NetworkAPIService getInstance() {
        if (deviceService == null) {
            deviceService = new NetworkAPIService();
        }
        return deviceService;
    }

    @Override
    public String getBaseApiUrl() {
        return  BASE_API_URL;
    }

    @Override
    protected DeviceApi getService(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return new Retrofit.Builder()
                .baseUrl(getBaseApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build().create(DeviceApi.class);
    }
    public interface DeviceApi {

        @GET("users")
        @Headers("Content-Type: application/json")
        Call<List<UsersPojo>> getuserdetails();

        @GET("users/{username}")
        @Headers("Content-Type: application/json")
        Call<UserDetailsPojo>getuserinfo(
                @Path("username") String username);

    }
}