package com.example.v2tech.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnHistoryDataListener;
import com.example.v2tech.model.SurveyDataModel;
import com.example.v2tech.model.SurveyHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class DropdownFragment extends Fragment {

    private TextView dropdownQues;
    private SurveyHistoryModel surveyHistoryModel;
    private int listPosition;
    private List<String> dropdownItemList;
    OnHistoryDataListener onHistoryDataListener;
    private SurveyDataModel surveyDataModel;
    private Spinner surveySpinner;

    public DropdownFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onHistoryDataListener = (OnHistoryDataListener) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dropdown, container, false);

        dropdownQues = view.findViewById(R.id.dropdownQues);
        surveySpinner = view.findViewById(R.id.surveySpinner);

        surveyDataModel = (SurveyDataModel) getArguments().getSerializable("dropdownObj");
        listPosition = (Integer) getArguments().get("listPosition");


        dropdownQues.setText(surveyDataModel.getQuestion());
        dropdownItemList = new ArrayList<>();
        dropdownItemList.add("Select from options");

        String[] result = surveyDataModel.getOptions().split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
            dropdownItemList.add(result[i]);
        }

        surveySpinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dropdownItemList));

        getSpinnerData();

        return view;
    }

    private void getSpinnerData() {
        surveySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){
                    surveyHistoryModel = new SurveyHistoryModel(surveyDataModel.getQuestion(),adapterView.getItemAtPosition(i).toString());
                    onHistoryDataListener.getHistoryData(listPosition,surveyHistoryModel);
                    onHistoryDataListener.requiredCheck(1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}