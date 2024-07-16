package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityTipsPageBinding;

import java.util.Arrays;
import java.util.List;

public class TipsPageActivity extends AppCompatActivity {

    ActivityTipsPageBinding tipsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tips_page);

        List<String> tips = Arrays.asList(
                "1. Separe los residuos orgánicos de los inorgánicos.",
                "2. Lave los envases antes de reciclarlos.",
                "3. Aplaste las botellas de plástico para ahorrar espacio.",
                "4. No recicle papel o cartón sucio.",
                "5. Utilice bolsas reutilizables para sus compras."
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tips);

        tipsBinding.listViewTips.setAdapter(adapter);
    }
}