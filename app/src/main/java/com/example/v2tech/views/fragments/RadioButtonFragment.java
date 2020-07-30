package com.example.v2tech.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnHistoryDataListener;
import com.example.v2tech.intefaces.OnSurveyCheckListener;
import com.example.v2tech.model.SurveyDataModel;
import com.example.v2tech.model.SurveyHistoryModel;
import com.example.v2tech.views.adapters.CheckboxAdapter;
import com.example.v2tech.views.adapters.RadioButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class RadioButtonFragment extends Fragment implements OnSurveyCheckListener {

    private SurveyDataModel surveyDataModel;
    private TextView radioButtonQues;
    private RecyclerView radioButtonRecycler;
    private List<String> radioButtonItemList;
    private List<String> radioButtonItemCurrentList = new ArrayList<>();
    private RadioButtonAdapter radioButtonAdapter;
    private SurveyHistoryModel surveyHistoryModel;
    private int listPosition;
    OnHistoryDataListener onHistoryDataListener;

    public RadioButtonFragment() {
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
        View view = inflater.inflate(R.layout.fragment_radio_button, container, false);

        radioButtonQues = view.findViewById(R.id.checkboxQues);
        radioButtonRecycler = view.findViewById(R.id.checkboxRecycler);

        surveyDataModel = (SurveyDataModel) getArguments().getSerializable("radioButtonObj");
        listPosition = (Integer) getArguments().get("listPosition");

//        Toast.makeText(getContext(), surveyDataModel.getQuestion(), Toast.LENGTH_SHORT).show();

        radioButtonQues.setText(surveyDataModel.getQuestion());
        radioButtonItemList = new ArrayList<>();

        String[] result = surveyDataModel.getOptions().split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
            radioButtonItemList.add(result[i]);
        }

        radioButtonAdapter = new RadioButtonAdapter(getContext(),radioButtonItemList,this);
        radioButtonRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        radioButtonRecycler.setAdapter(radioButtonAdapter);
        return view;
    }

    @Override
    public void onItemCheck(String surveyData) {

        surveyHistoryModel = new SurveyHistoryModel(surveyDataModel.getQuestion(),surveyData);
        onHistoryDataListener.getHistoryData(listPosition,surveyHistoryModel);
        onHistoryDataListener.requiredCheck(1);
        Log.d("CheckData",surveyData);
    }

    @Override
    public void onItemUncheck(String surveyData) {
    }
}