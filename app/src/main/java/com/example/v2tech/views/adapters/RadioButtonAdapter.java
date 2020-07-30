package com.example.v2tech.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnSurveyCheckListener;

import java.util.List;

public class RadioButtonAdapter extends RecyclerView.Adapter<RadioButtonAdapter.RadioButtonViewHolder> {

    private Context context;
    private List<String> radioButtonItemList;
    private OnSurveyCheckListener onSurveyCheckListener;

    public RadioButtonAdapter(Context context, List<String> radioButtonItemList, OnSurveyCheckListener onSurveyCheckListener) {
        this.context = context;
        this.radioButtonItemList = radioButtonItemList;
        this.onSurveyCheckListener = onSurveyCheckListener;
    }

    @NonNull
    @Override
    public RadioButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_radio_btn_item,parent,false);
        return new RadioButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RadioButtonViewHolder holder, final int position) {

        holder.surveyRadioBtn.setText(radioButtonItemList.get(position));

        holder.surveyRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onSurveyCheckListener.onItemCheck(radioButtonItemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("radioCount",radioButtonItemList.size()+"");
        return radioButtonItemList.size();
    }

    public class RadioButtonViewHolder extends RecyclerView.ViewHolder {

        private RadioButton surveyRadioBtn;

        public RadioButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            surveyRadioBtn = itemView.findViewById(R.id.surveyRadioBtn);
        }
    }
}
