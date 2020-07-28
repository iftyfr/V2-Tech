package com.example.v2tech.networks.ApiUtil;

import android.util.Log;

import com.example.v2tech.networks.Remote.APIService;
import com.example.v2tech.networks.Remote.RetroClient;


public class ApiUtils {
    public static APIService getApiService(String baseURL){
        Log.d("responsedata", baseURL);
        return RetroClient.getClient(baseURL).create(APIService.class);
    }
}
