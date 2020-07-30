package com.example.v2tech.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnSurveyCheckListener;
import com.example.v2tech.views.activities.SurveyDetailsActivity;

import java.util.List;

public class SurveyHistoryAdapter extends RecyclerView.Adapter<SurveyHistoryAdapter.SurveyHistoryViewHolder> {

    private Context context;
    private List<String> surveyHistoryList;

    public SurveyHistoryAdapter(Context context, List<String> surveyHistoryList) {
        this.context = context;
        this.surveyHistoryList = surveyHistoryList;
    }

    @NonNull
    @Override
    public SurveyHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_survey_history,parent,false);
        return new SurveyHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SurveyHistoryViewHolder holder, final int position) {

        holder.surveyHistoryItem.setText("Survey "+(position+1));

        holder.surveyHistoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,SurveyDetailsActivity.class).putExtra("surveyData",surveyHistoryList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return surveyHistoryList.size();
    }

    public class SurveyHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView surveyHistoryItem;

        public SurveyHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            surveyHistoryItem = itemView.findViewById(R.id.surveyHistoryItem);
        }
    }
}
