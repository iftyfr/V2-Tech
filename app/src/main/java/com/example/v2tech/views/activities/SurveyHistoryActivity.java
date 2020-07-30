package com.example.v2tech.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.v2tech.R;
import com.example.v2tech.model.SurveyDatabaseManager;
import com.example.v2tech.views.adapters.CheckboxAdapter;
import com.example.v2tech.views.adapters.SurveyHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SurveyHistoryActivity extends AppCompatActivity {


    private SurveyDatabaseManager databaseManager;
    private List<String> surveyList = new ArrayList<>();
    private SurveyHistoryAdapter surveyHistoryAdapter;
    private RecyclerView surveyHistoryRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_history);

        Toolbar myToolbar = findViewById(R.id.toolbarSurvHistory);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("Previous Surveys");

        surveyHistoryRecycler = findViewById(R.id.surveyHistoryRecycler);
        databaseManager = new SurveyDatabaseManager(this);
        surveyList = databaseManager.getAllSurvey();
        
        if (surveyList.size()<1){
            Toasty.warning(this, "There is no survey report!", Toast.LENGTH_LONG).show();
        }

        surveyHistoryAdapter = new SurveyHistoryAdapter(this,surveyList);
        surveyHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        surveyHistoryRecycler.setAdapter(surveyHistoryAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}