package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.adapters.StatisticsAdapter;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityStatisticsPageBinding;
import com.machdevs.ecoreciclaappmobile.models.RecyclingCategory;
import com.machdevs.ecoreciclaappmobile.models.StatisticItem;
import com.machdevs.ecoreciclaappmobile.utils.RecyclingManager;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPageActivity extends AppCompatActivity {

    private ActivityStatisticsPageBinding binding;
    private RecyclingManager recyclingManager;
    private StatisticsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statistics_page);
        recyclingManager = new RecyclingManager(this);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setupRecyclerView();
        setupSummary();
    }

    private void setupRecyclerView() {
        List<StatisticItem> statisticItems = generateStatistics();
        adapter = new StatisticsAdapter(this, statisticItems);
        binding.recyclerViewStatistics.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewStatistics.setAdapter(adapter);
    }

    private void setupSummary() {
        double totalRecycled = 0;
        double totalValue = 0;
        for (RecyclingCategory category : recyclingManager.getCategories()) {
            totalRecycled += recyclingManager.calculateTotalAmountByCategory(category);
            totalValue += recyclingManager.calculateTotalValueByCategory(category);
        }

        binding.tvTotalRecycled.setText(String.format("Total reciclado: %.2f kg", totalRecycled));
        binding.tvTotalValue.setText(String.format("Valor total: $%.2f", totalValue));
    }

    private List<StatisticItem> generateStatistics() {
        List<StatisticItem> items = new ArrayList<>();
        for (RecyclingCategory category : recyclingManager.getCategories()) {
            double average = recyclingManager.calculateAverageByCategory(category);
            double max = recyclingManager.calculateMaxByCategory(category);
            double min = recyclingManager.calculateMinByCategory(category);
            double totalValue = recyclingManager.calculateTotalValueByCategory(category);
            double totalAmount = recyclingManager.calculateTotalAmountByCategory(category);

            items.add(new StatisticItem(category.getName(), average, max, min, totalValue, totalAmount, category.getUnit()));
        }
        return items;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}