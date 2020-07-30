package com.example.v2tech.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2tech.R;
import com.example.v2tech.model.SurveyHistoryModel;
import com.example.v2tech.views.activities.SurveyDetailsActivity;

import java.util.List;

public class SurveyHistoryDetailsAdapter extends RecyclerView.Adapter<SurveyHistoryDetailsAdapter.SurveyHistoryDetailsViewHolder> {

    private Context context;
    private List<SurveyHistoryModel> surveyHistoryList;

    public SurveyHistoryDetailsAdapter(Context context, List<SurveyHistoryModel> surveyHistoryList) {
        this.context = context;
        this.surveyHistoryList = surveyHistoryList;
    }

    @NonNull
    @Override
    public SurveyHistoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_survey_details_history, parent, false);
        return new SurveyHistoryDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SurveyHistoryDetailsViewHolder holder, final int position) {
        SurveyHistoryModel surveyHistoryModel = surveyHistoryList.get(position);

        holder.surveyHistoryQues.setText("Ques: " + (position + 1)+". "+surveyHistoryModel.getQuestion());
        holder.surveyHistoryAns.setText("Ans: " +surveyHistoryModel.getAnswer());


    }

    @Override
    public int getItemCount() {
        return surveyHistoryList.size();
    }

    public class SurveyHistoryDetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView surveyHistoryQues, surveyHistoryAns;

        public SurveyHistoryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            surveyHistoryQues = itemView.findViewById(R.id.surveyHistoryQues);
            surveyHistoryAns = itemView.findViewById(R.id.surveyHistoryAns);
        }
    }
}
