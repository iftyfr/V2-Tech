package com.example.v2tech.presenters;

import com.example.v2tech.intefaces.OnRequestComplete;
import com.example.v2tech.intefaces.OnSurveyDataView;
import com.example.v2tech.model.InvokeSurveyDataApi;
import com.example.v2tech.model.SurveyDataModel;

import java.util.HashMap;

public class SurveyDataPresenter {
    private OnSurveyDataView onSurveyDataView;

    public SurveyDataPresenter(OnSurveyDataView onSurveyDataView) {
        this.onSurveyDataView = onSurveyDataView;
    }
    public void getSurveyData(){
        onSurveyDataView.onSurveyStartLoading();
        new InvokeSurveyDataApi(new OnRequestComplete() {
            @Override
            public void onRequestSuccess(Object obj) {
                onSurveyDataView.onSurveyStopLoading();
                onSurveyDataView.onSurveyData((SurveyDataModel) obj);
            }

            @Override
            public void onRequestError(String errMsg) {
                onSurveyDataView.onSurveyStopLoading();
                onSurveyDataView.onSurveyShowMessage(errMsg);
            }
        });
    }
}
