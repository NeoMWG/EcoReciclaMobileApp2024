package com.machdevs.ecoreciclaappmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.models.StatisticItem;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {

    private List<StatisticItem> statisticItems;

    public StatisticsAdapter(List<StatisticItem> statisticItems) {
        this.statisticItems = statisticItems;
    }

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistics, parent, false);
        return new StatisticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position) {
        StatisticItem item = statisticItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return statisticItems.size();
    }

    static class StatisticsViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName, tvAverageAmount, tvMaxAmount, tvMinAmount, tvTotalValue;

        StatisticsViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvAverageAmount = itemView.findViewById(R.id.tvAverageAmount);
            tvMaxAmount = itemView.findViewById(R.id.tvMaxAmount);
            tvMinAmount = itemView.findViewById(R.id.tvMinAmount);
            tvTotalValue = itemView.findViewById(R.id.tvTotalValue);
        }

        void bind(StatisticItem item) {
            tvCategoryName.setText(item.getCategoryName());
            tvAverageAmount.setText(String.format("Promedio: %.2f %s", item.getAverageAmount(), item.getUnit()));
            tvMaxAmount.setText(String.format("Máximo: %.2f %s", item.getMaxAmount(), item.getUnit()));
            tvMinAmount.setText(String.format("Mínimo: %.2f %s", item.getMinAmount(), item.getUnit()));
            tvTotalValue.setText(String.format("Valor total: $%.2f", item.getTotalValue()));
        }
    }
}