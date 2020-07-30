package com.example.v2tech.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnHistoryDataListener;
import com.example.v2tech.model.SurveyDataModel;
import com.example.v2tech.model.SurveyHistoryModel;
import com.google.android.material.textfield.TextInputLayout;

public class EditTextFragment extends Fragment {

    private TextInputLayout textInputLayout;
    private EditText editText;
    private TextView editTextQues;
    private SurveyHistoryModel surveyHistoryModel;
    private int listPosition;
    OnHistoryDataListener onHistoryDataListener;
    private SurveyDataModel surveyDataModel;
    private String type;

    public EditTextFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);

        surveyDataModel = (SurveyDataModel) getArguments().getSerializable("editTextObj");
        listPosition = (Integer) getArguments().get("listPosition");

        textInputLayout = view.findViewById(R.id.text_input_layout);
        editText = view.findViewById(R.id.editText);
        editTextQues = view.findViewById(R.id.editTextQues);

            editText.setInputType(InputType.TYPE_CLASS_TEXT);


        editTextQues.setText(surveyDataModel.getQuestion());

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length()>0){
                    surveyHistoryModel = new SurveyHistoryModel(surveyDataModel.getQuestion(),charSequence.toString());
                    onHistoryDataListener.getHistoryData(listPosition,surveyHistoryModel);
                    onHistoryDataListener.requiredCheck(1);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}