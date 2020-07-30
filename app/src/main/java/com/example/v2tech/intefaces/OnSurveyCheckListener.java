package com.example.v2tech.intefaces;

import com.example.v2tech.model.SurveyDataModel;

public interface OnSurveyCheckListener {

    void onItemCheck(String surveyData);
    void onItemUncheck(String surveyData);
}
