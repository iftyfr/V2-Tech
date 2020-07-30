package com.example.v2tech.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2tech.R;
import com.example.v2tech.intefaces.OnSurveyCheckListener;
import com.example.v2tech.model.SurveyDataModel;

import java.util.List;

public class CheckboxAdapter extends RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder> {

    private Context context;
    private List<String> checboxItemList;
    private OnSurveyCheckListener onSurveyCheckListener;

    public CheckboxAdapter(Context context, List<String> checboxItemList, OnSurveyCheckListener onSurveyCheckListener) {
        this.context = context;
        this.checboxItemList = checboxItemList;
        this.onSurveyCheckListener = onSurveyCheckListener;
    }

    @NonNull
    @Override
    public CheckboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_checkbox_item,parent,false);
        return new CheckboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CheckboxViewHolder holder, final int position) {

        holder.surveyCheckBox.setText(checboxItemList.get(position));

        holder.surveyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.surveyCheckBox.isChecked()){
                    onSurveyCheckListener.onItemCheck(checboxItemList.get(position));
                    //holder.categoryCheckBox.setChecked(false);
                }else {
                    onSurveyCheckListener.onItemUncheck(checboxItemList.get(position));
                    //holder.categoryCheckBox.setChecked(true);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return checboxItemList.size();
    }

    public class CheckboxViewHolder extends RecyclerView.ViewHolder {

        private CheckBox surveyCheckBox;

        public CheckboxViewHolder(@NonNull View itemView) {
            super(itemView);
            surveyCheckBox = itemView.findViewById(R.id.surveyCheckBox);
        }
    }
}
