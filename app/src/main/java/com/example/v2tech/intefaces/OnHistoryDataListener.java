package com.example.v2tech.intefaces;

import com.example.v2tech.model.SurveyHistoryModel;

public interface OnHistoryDataListener {
    void getHistoryData(int surveyListPosition, SurveyHistoryModel surveyHistoryModel);
    void requiredCheck(int checkData);
}
