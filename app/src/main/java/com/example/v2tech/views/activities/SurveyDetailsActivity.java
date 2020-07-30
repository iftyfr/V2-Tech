package com.example.v2tech.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.v2tech.R;
import com.example.v2tech.model.SurveyHistoryModel;
import com.example.v2tech.views.adapters.SurveyHistoryAdapter;
import com.example.v2tech.views.adapters.SurveyHistoryDetailsAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class SurveyDetailsActivity extends AppCompatActivity {

    private String surveyData;
    private List<SurveyHistoryModel> surveyHistoryList = new ArrayList<>();
    private SurveyHistoryDetailsAdapter surveyHistoryDetailsAdapter;
    private RecyclerView surveyDetailsHistoryRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_details);

        Toolbar myToolbar = findViewById(R.id.toolbarSurveyDetails);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("Survey Details");

        surveyDetailsHistoryRecycler = findViewById(R.id.surveyDetailsHistoryRecycler);

        surveyData = getIntent().getStringExtra("surveyData");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SurveyHistoryModel>>() {}.getType();

        surveyHistoryList = gson.fromJson(surveyData, type);

        surveyHistoryDetailsAdapter = new SurveyHistoryDetailsAdapter(this,surveyHistoryList);
        surveyDetailsHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        surveyDetailsHistoryRecycler.setAdapter(surveyHistoryDetailsAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}