package com.machdevs.ecoreciclaappmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.models.StatisticItem;
import com.machdevs.ecoreciclaappmobile.views.BarChartView;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {

    private List<StatisticItem> statisticItems;
    private Context context;
    private int[] colors;
    private double totalAmount;

    public StatisticsAdapter(Context context, List<StatisticItem> statisticItems) {
        this.context = context;
        this.statisticItems = statisticItems;
        this.totalAmount = calculateTotalAmount();
        this.colors = new int[]{
                ContextCompat.getColor(context, R.color.chathamsBlue),
                ContextCompat.getColor(context, R.color.jade),
                ContextCompat.getColor(context, R.color.amber),
                ContextCompat.getColor(context, R.color.copper),
                ContextCompat.getColor(context, R.color.indigo)
        };
    }

    private double calculateTotalAmount() {
        double total = 0;
        for (StatisticItem item : statisticItems) {
            total += item.getTotalAmount();
        }
        return total;
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
        int color = colors[position % colors.length];
        float percentage = (float) ((item.getTotalAmount() / totalAmount) * 100);
        holder.bind(item, color, percentage);
    }

    @Override
    public int getItemCount() {
        return statisticItems.size();
    }

    class StatisticsViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName, tvAverageAmount, tvMaxAmount, tvMinAmount, tvTotalValue, tvTotalAmount;
        BarChartView barChart;

        StatisticsViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvAverageAmount = itemView.findViewById(R.id.tvAverageAmount);
            tvMaxAmount = itemView.findViewById(R.id.tvMaxAmount);
            tvMinAmount = itemView.findViewById(R.id.tvMinAmount);
            tvTotalValue = itemView.findViewById(R.id.tvTotalValue);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            barChart = itemView.findViewById(R.id.barChart);
        }

        void bind(StatisticItem item, int color, float percentage) {
            tvCategoryName.setText(item.getCategoryName());
            tvAverageAmount.setText(String.format("Promedio: %.2f %s", item.getAverageAmount(), item.getUnit()));
            tvMaxAmount.setText(String.format("Máximo: %.2f %s", item.getMaxAmount(), item.getUnit()));
            tvMinAmount.setText(String.format("Mínimo: %.2f %s", item.getMinAmount(), item.getUnit()));
            tvTotalValue.setText(String.format("Valor total: $%.2f", item.getTotalValue()));
            tvTotalAmount.setText(String.format("Cantidad total: %.2f %s", item.getTotalAmount(), item.getUnit()));

            barChart.setPercentage(percentage, color);
        }
    }
}