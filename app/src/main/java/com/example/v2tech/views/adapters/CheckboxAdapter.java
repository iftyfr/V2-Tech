package com.example.v2tech.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2tech.R;

import java.util.List;

public class CheckboxAdapter extends RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder> {

    private Context context;
    private List<SearchCategoryModel> searchCategoryList;
    private OnCategoryCheckListener onCategoryCheckListener;

    public CheckboxAdapter(Context context, List<SearchCategoryModel> searchCategoryList, OnCategoryCheckListener onCategoryCheckListener) {
        this.context = context;
        this.searchCategoryList = searchCategoryList;
        this.onCategoryCheckListener = onCategoryCheckListener;
    }

    @NonNull
    @Override
    public CheckboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_checkbox_item,parent,false);
        return new SearchCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckboxViewHolder holder, int position) {

        SearchCategoryModel searchCategoryModel = searchCategoryList.get(position);
        holder.categoryCheckBox.setText(searchCategoryModel.getTitle());

        holder.categoryCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.categoryCheckBox.isChecked()){
                    onCategoryCheckListener.onItemCheck(searchCategoryModel);
                    //holder.categoryCheckBox.setChecked(false);
                }else {
                    onCategoryCheckListener.onItemUncheck(searchCategoryModel);
                    //holder.categoryCheckBox.setChecked(true);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return searchCategoryList.size();
    }

    public class CheckboxViewHolder extends RecyclerView.ViewHolder {

        private CheckBox categoryCheckBox;

        public CheckboxViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryCheckBox = itemView.findViewById(R.id.categoryCheckBox);
        }
    }
}
