package com.example.v2tech.networks.Remote;
import com.example.v2tech.model.SurveyDataModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getSurvey")
    Call<SurveyDataModel> getSurveyData();
}
