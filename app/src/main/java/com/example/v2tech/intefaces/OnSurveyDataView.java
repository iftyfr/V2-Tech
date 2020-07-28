package com.example.v2tech.intefaces;
import com.example.v2tech.model.SurveyDataModel;

public interface OnSurveyDataView {
    void onSurveyData(SurveyDataModel surveyDataModel);

    void onSurveyStartLoading();

    void onSurveyStopLoading();

    void onSurveyShowMessage(String errMsg);
}
