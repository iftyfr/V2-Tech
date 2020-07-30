package com.example.v2tech.networks.Remote;
import com.example.v2tech.model.SurveyDataModel;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface APIService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("getSurvey")
    Call<List<SurveyDataModel>> getSurveyData(@Header("Date") String timestamp);
}
