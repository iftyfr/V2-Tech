package com.example.v2tech.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnHistoryDataListener;
import com.example.v2tech.intefaces.OnSurveyCheckListener;
import com.example.v2tech.model.SurveyDataModel;
import com.example.v2tech.model.SurveyHistoryModel;
import com.example.v2tech.views.adapters.CheckboxAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckboxFragment extends Fragment implements OnSurveyCheckListener {

    private SurveyDataModel surveyDataModel;
    private TextView checkboxQues;
    private RecyclerView checkboxRecycler;
    private List<String> checboxItemList;
    private List<String> checboxItemCurrentList = new ArrayList<>();
    private CheckboxAdapter checkboxAdapter;
    private SurveyHistoryModel surveyHistoryModel;
    private int listPosition;
    OnHistoryDataListener onHistoryDataListener;

    public CheckboxFragment() {
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
        View view = inflater.inflate(R.layout.fragment_question_one, container, false);

        checkboxQues = view.findViewById(R.id.checkboxQues);
        checkboxRecycler = view.findViewById(R.id.checkboxRecycler);

        surveyDataModel = (SurveyDataModel) getArguments().getSerializable("checkboxObj");
        listPosition = (Integer) getArguments().get("listPosition");


        checkboxQues.setText(surveyDataModel.getQuestion());
        checboxItemList = new ArrayList<>();
        String[] result = surveyDataModel.getOptions().split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
            checboxItemList.add(result[i]);
        }

        checkboxAdapter = new CheckboxAdapter(getContext(),checboxItemList,this);
        checkboxRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        checkboxRecycler.setAdapter(checkboxAdapter);
        return view;
    }

    @Override
    public void onItemCheck(String surveyData) {
        String items = "" ;
        checboxItemCurrentList.add(surveyData);

        for (int i =0;i<checboxItemCurrentList.size();i++){
            items = items+","+checboxItemCurrentList.get(i);
        }
        surveyHistoryModel = new SurveyHistoryModel(surveyDataModel.getQuestion(),items.substring(1));
        onHistoryDataListener.getHistoryData(listPosition,surveyHistoryModel);
        onHistoryDataListener.requiredCheck(1);
        Log.d("CheckData",items);
    }

    @Override
    public void onItemUncheck(String surveyData) {
        String items = "" ;
        checboxItemCurrentList.remove(surveyData);

        for (int i =0;i<checboxItemCurrentList.size();i++){
            items = items+","+checboxItemCurrentList.get(i);
        }
        if (items.length()<3){
            onHistoryDataListener.requiredCheck(0);
        }else {
            Log.d("CheckData",items);
            surveyHistoryModel = new SurveyHistoryModel(surveyDataModel.getQuestion(),items.substring(1));
            onHistoryDataListener.getHistoryData(listPosition,surveyHistoryModel);
        }
    }
}