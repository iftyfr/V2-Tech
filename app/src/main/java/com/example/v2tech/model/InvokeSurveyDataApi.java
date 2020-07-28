package com.example.v2tech.model;

import android.util.Log;

import com.example.v2tech.intefaces.OnRequestComplete;
import com.example.v2tech.networks.ApiUtil.ApiUtils;
import com.example.v2tech.networks.Remote.APIService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvokeSurveyDataApi {
    private OnRequestComplete requestComplete;

    public InvokeSurveyDataApi(final OnRequestComplete requestComplete) {
        this.requestComplete = requestComplete;

        APIService apiService = ApiUtils.getApiService("https://example-response.herokuapp.com/");
        apiService.getSurveyData().enqueue(new Callback<SurveyDataModel>() {
            @Override
            public void onResponse(Call<SurveyDataModel> call, Response<SurveyDataModel> response) {
                if (response.isSuccessful()){
                        requestComplete.onRequestSuccess(response.body());
                }
                else {
                    requestComplete.onRequestError(""+response.message());
                }
            }

            @Override
            public void onFailure(Call<SurveyDataModel> call, Throwable t) {
                Log.d("ErrorOnApi",t.getMessage());
                requestComplete.onRequestError("Something went wrong!");

            }
        });
    }
}
