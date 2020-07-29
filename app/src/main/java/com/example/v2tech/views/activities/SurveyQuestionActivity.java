package com.example.v2tech.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.v2tech.R;

public class SurveyQuestionActivity extends AppCompatActivity {

    private ProgressBar surveyProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question);

        surveyProgressBar = findViewById(R.id.surveyProgressBar);

        surveyProgressBar.setScaleY(2f);
        surveyProgressBar.setProgress(50);
    }

    public void nextSurveyQues(View view) {
    }

    public void prevSurvey(View view) {
    }
}