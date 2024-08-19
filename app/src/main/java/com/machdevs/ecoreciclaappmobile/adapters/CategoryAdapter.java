package com.machdevs.ecoreciclaappmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.models.RecyclingCategory;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<RecyclingCategory> categories;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(RecyclingCategory category);
    }

    public CategoryAdapter(List<RecyclingCategory> categories, OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        RecyclingCategory category = categories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoryBackground;
        TextView tvCategoryName;
        TextView tvCategoryUnit;
        TextView tvCategoryDescription;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ivCategoryBackground = itemView.findViewById(R.id.ivCategoryBackground);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvCategoryUnit = itemView.findViewById(R.id.tvCategoryUnit);
            tvCategoryDescription = itemView.findViewById(R.id.tvCategoryDescription);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onCategoryClick(categories.get(position));
                }
            });
        }

        void bind(RecyclingCategory category) {
            tvCategoryName.setText(category.getName());
            tvCategoryUnit.setText(category.getUnit());
            tvCategoryDescription.setText(category.getDescription());
            ivCategoryBackground.setImageResource(category.getBackgroundImageResId());
        }
    }
}