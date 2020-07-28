package com.example.v2tech.networks.Remote;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(String bsURL) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(bsURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            if (!retrofit.baseUrl().equals(bsURL)) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(bsURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return retrofit;
    }

}
