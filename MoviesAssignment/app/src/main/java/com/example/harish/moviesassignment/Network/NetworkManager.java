package com.example.harish.moviesassignment.Network;

import com.example.harish.moviesassignment.Network.Services.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harish Reddy on 2/5/2017.
 */

public class NetworkManager {

    public static final String BaseURL="http://www.omdbapi.com";

    private Retrofit mRetrofit;

    private Services mServices;
    public NetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServices = mRetrofit.create(Services.class);
    }
    public Services getmServices(){
        return mServices;
    }
}

