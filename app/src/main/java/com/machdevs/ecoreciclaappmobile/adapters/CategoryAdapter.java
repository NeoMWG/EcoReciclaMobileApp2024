package com.machdevs.ecoreciclaappmobile.adapters;

import android.content.Context;
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
    private Context context;

    public interface OnCategoryClickListener {
        void onCategoryClick(RecyclingCategory category);
    }

    public CategoryAdapter(Context context, List<RecyclingCategory> categories, OnCategoryClickListener listener) {
        this.context = context;
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
        return categories != null ? categories.size() : 0;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName, tvCategoryUnit, tvCategoryDescription;
        ImageView ivCategoryBackground;

        CategoryViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvCategoryUnit = itemView.findViewById(R.id.tvCategoryUnit);
            tvCategoryDescription = itemView.findViewById(R.id.tvCategoryDescription);
            ivCategoryBackground = itemView.findViewById(R.id.ivCategoryBackground);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onCategoryClick(categories.get(position));
                }
            });
        }

        void bind(RecyclingCategory category) {
            if (category == null) return;

            tvCategoryName.setText(category.getName());
            tvCategoryUnit.setText(category.getUnit());
            tvCategoryDescription.setText(getCategoryDescription(category.getName()));
            ivCategoryBackground.setImageResource(getCategoryBackground(category.getName()));
        }
    }

    private String getCategoryDescription(String categoryName) {
        switch (categoryName.toLowerCase()) {
            case "plástico":
                return "Botellas, envases y bolsas plásticas";
            case "papel":
                return "Periódicos, revistas y cajas de cartón";
            case "vidrio":
                return "Botellas y frascos de vidrio";
            case "metal":
                return "Latas de aluminio y envases metálicos";
            case "orgánico":
                return "Restos de comida y residuos de jardín";
            default:
                return "Otros materiales reciclables";
        }
    }

    private int getCategoryBackground(String categoryName) {
        switch (categoryName.toLowerCase()) {
            case "plástico":
                return R.drawable.background_plastic;
            case "papel":
                return R.drawable.background_paper;
            case "vidrio":
                return R.drawable.background_glass;
            case "metal":
                return R.drawable.background_metal;
            case "orgánico":
                return R.drawable.background_organic;
            default:
                return R.drawable.default_recycling;
        }
    }

    public void updateCategories(List<RecyclingCategory> newCategories) {
        this.categories = newCategories;
        notifyDataSetChanged();
    }
}