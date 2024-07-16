package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityStatisticsPageBinding;

import java.util.Arrays;
import java.util.List;

public class StatisticsPageActivity extends AppCompatActivity {

    ActivityStatisticsPageBinding statisticsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statisticsBinding = DataBindingUtil.setContentView(this, R.layout.activity_statistics_page);

        List<String> statistics = Arrays.asList(
                "Plástico Reciclado: 5 kg",
                "Papel y Cartón Reciclado: 3 kg",
                "Vidrio Reciclado: 2 kg",
                "Metales Reciclados: 1 kg",
                "Orgánicos Reciclados: 4 kg"
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, statistics);

        statisticsBinding.listViewStatistics.setAdapter(adapter);
    }
}