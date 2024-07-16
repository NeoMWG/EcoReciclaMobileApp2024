package com.machdevs.ecoreciclaappmobile.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityPrincipalPageBinding;

public class PrincipalPageActivity extends AppCompatActivity {

    ActivityPrincipalPageBinding principalPageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        principalPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_principal_page);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        principalPageBinding.btnCategories.setOnClickListener(v -> {
            Intent intent = new Intent(PrincipalPageActivity.this, CategoriesPageActivity.class);
            startActivity(intent);
        });

        principalPageBinding.btnStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(PrincipalPageActivity.this, StatisticsPageActivity.class);
            startActivity(intent);
        });

        principalPageBinding.btnTips.setOnClickListener(v -> {
            Intent intent = new Intent(PrincipalPageActivity.this, TipsPageActivity.class);
            startActivity(intent);
        });
    }
}