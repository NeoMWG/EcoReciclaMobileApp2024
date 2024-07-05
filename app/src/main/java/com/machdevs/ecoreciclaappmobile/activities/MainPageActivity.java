package com.machdevs.ecoreciclaappmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityMainBinding;

public class MainPageActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupListeners();
    }

    private void setupListeners() {
        mainBinding.btnGoToLoginMa.setOnClickListener(this::onGoToLoginPageButtonClicked);
    }

    private void onGoToLoginPageButtonClicked(View view) {
        navigatorToLoginPage();
    }

    private void navigatorToLoginPage() {
        Intent intent = new Intent(MainPageActivity.this, LoginPageActivity.class);
        startActivity(intent);
    }
}