package com.example.v2tech.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnHistoryDataListener;
import com.example.v2tech.intefaces.OnSurveyDataView;
import com.example.v2tech.model.SurveyDataModel;
import com.example.v2tech.model.SurveyDatabaseManager;
import com.example.v2tech.model.SurveyHistoryModel;
import com.example.v2tech.presenters.SurveyDataPresenter;
import com.example.v2tech.views.fragments.CheckboxFragment;
import com.example.v2tech.views.fragments.DropdownFragment;
import com.example.v2tech.views.fragments.EditTextFragment;
import com.example.v2tech.views.fragments.NumberInputFragment;
import com.example.v2tech.views.fragments.RadioButtonFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class SurveyQuestionActivity extends AppCompatActivity implements OnSurveyDataView, OnHistoryDataListener {

    private ProgressBar surveyProgressBar;
    private SurveyDataPresenter surveyDataPresenter;
    private List<SurveyDataModel> surveyDataList = new ArrayList<>();
    private CheckboxFragment checkboxFragment = new CheckboxFragment();
    private EditTextFragment editTextFragment = new EditTextFragment();
    private NumberInputFragment numberInputFragment = new NumberInputFragment();
    private RadioButtonFragment radioButtonFragment = new RadioButtonFragment();
    private DropdownFragment dropdownFragment = new DropdownFragment();
    private MaterialDialog dialog;
    private int count = 0;
    private List<SurveyHistoryModel> surveyHistoryList = new ArrayList<>();
    private int dataCheck = 0;
    private Button nextBtn;
    private SurveyDatabaseManager databaseManager;
    private SurveyHistoryModel surveyHistoryModel;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_question);


        Toolbar myToolbar = findViewById(R.id.toolbarNewSurvey);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("Survey");

        surveyProgressBar = findViewById(R.id.surveyProgressBar);
        nextBtn = findViewById(R.id.nextBtn);

        databaseManager = new SurveyDatabaseManager(this);

        surveyProgressBar.setScaleY(2f);

        dialog = new MaterialDialog.Builder(this).title(getResources().getString(R.string.loading))
                .content(getResources().getString(R.string.pleaseWait))
                .progress(true, 0)
                .cancelable(false)
                .build();

        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        surveyDataPresenter = new SurveyDataPresenter(this);
        surveyDataPresenter.getSurveyData(timeStamp);
    }

//...........  Survey Data from api .............................

    @Override
    public void onSurveyData(List<SurveyDataModel> surveyDataModelList) {
        surveyDataList = surveyDataModelList;

        progress = 100 / surveyDataModelList.size();
        checkSurveyType(surveyDataList.get(count).getType());
    }

    @Override
    public void onSurveyStartLoading() {
        dialog.show();
    }

    @Override
    public void onSurveyStopLoading() {
        dialog.dismiss();
    }

    @Override
    public void onSurveyShowMessage(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }


    //................... next button ..........................

    public void nextSurveyQues(View view) {


        if (surveyDataList.get(count).getRequired() == true) {
            if (dataCheck == 1) {
                if (count < surveyDataList.size()) {
                    count++;
                    surveyHistoryList.add(surveyHistoryModel);

                    surveyProgressBar.setProgress(progress*count);
                    if (count == surveyDataList.size()) {
                        saveSurveyDataPopup();
                        return;
                    }
                    checkSurveyType(surveyDataList.get(count).getType());
                    dataCheck = 0;

                }
            } else {
                Toasty.warning(this, "It's a required field!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (count < surveyDataList.size()) {
                count++;
                surveyProgressBar.setProgress(progress*count);
                surveyHistoryList.add(surveyHistoryModel);
                if (count == surveyDataList.size()) {
                    saveSurveyDataPopup();
                    return;
                }
                checkSurveyType(surveyDataList.get(count).getType());
                dataCheck = 0;

            }

        }

    }


    public void startAgainSurvey(View view) {
        finish();
        startActivity(new Intent(SurveyQuestionActivity.this, SurveyQuestionActivity.class));

    }


    //.............. Fragment Replace ......................

    public void checkSurveyType(String type) {
        if (type.equals("multiple choice")) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("checkboxObj", surveyDataList.get(count));
            bundle.putInt("listPosition", count);
            checkboxFragment.setArguments(bundle);
            replaceFragment(checkboxFragment);
        } else if (type.equals("Checkbox")) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("radioButtonObj", surveyDataList.get(count));
            bundle.putInt("listPosition", count);
            radioButtonFragment.setArguments(bundle);
            replaceFragment(radioButtonFragment);
        } else if (type.equals("dropdown")) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("dropdownObj", surveyDataList.get(count));
            bundle.putInt("listPosition", count);
            dropdownFragment.setArguments(bundle);
            replaceFragment(dropdownFragment);
        } else if (type.equals("text")) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("editTextObj", surveyDataList.get(count));
            bundle.putInt("listPosition", count);
            editTextFragment.setArguments(bundle);
            replaceFragment(editTextFragment);
        } else if (type.equals("number")) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("numberObj", surveyDataList.get(count));
            bundle.putInt("listPosition", count);
            numberInputFragment.setArguments(bundle);
            replaceFragment(numberInputFragment);
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.surveyContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void getHistoryData(int surveyListPosition, SurveyHistoryModel surveyHistoryModel) {
        this.surveyHistoryModel = surveyHistoryModel;
    }

    @Override
    public void requiredCheck(int checkData) {
        dataCheck = checkData;
    }

    void saveSurveyDataPopup() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dataSave = builder.create();
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final View customView = layoutInflater.inflate(R.layout.popup_save_data, null);
        LinearLayout cancelData = (LinearLayout) customView.findViewById(R.id.cancelData);
        LinearLayout saveData = (LinearLayout) customView.findViewById(R.id.saveData);

        cancelData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SurveyQuestionActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                dataSave.dismiss();
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Gson gson = new Gson();
                String inputString = gson.toJson(surveyHistoryList);

                long isInserted = databaseManager.insertSurveyData(inputString);
                if (isInserted > 0) {
                    startActivity(new Intent(SurveyQuestionActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                    Toasty.success(SurveyQuestionActivity.this, "Survey Data Saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toasty.error(SurveyQuestionActivity.this, "Failed to save data.", Toast.LENGTH_SHORT).show();
                }

                dataSave.dismiss();
            }
        });
        dataSave.setCancelable(false);
        dataSave.setView(customView);
        dataSave.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}