package com.example.v2tech.intefaces;
import com.example.v2tech.model.SurveyDataModel;

import java.util.List;

public interface OnSurveyDataView {
    void onSurveyData(List<SurveyDataModel> surveyDataModel);

    void onSurveyStartLoading();

    void onSurveyStopLoading();

    void onSurveyShowMessage(String errMsg);
}
