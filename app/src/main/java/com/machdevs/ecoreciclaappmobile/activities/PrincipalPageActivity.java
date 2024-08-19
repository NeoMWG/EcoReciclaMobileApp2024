package com.machdevs.ecoreciclaappmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityPrincipalPageBinding;
import com.machdevs.ecoreciclaappmobile.utils.RecyclingManager;
import com.machdevs.ecoreciclaappmobile.utils.SharedPreferencesHelper;

public class PrincipalPageActivity extends AppCompatActivity {

    private ActivityPrincipalPageBinding binding;
    private SharedPreferencesHelper prefsHelper;
    private RecyclingManager recyclingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_principal_page);
        prefsHelper = new SharedPreferencesHelper(this);
        recyclingManager = new RecyclingManager(this);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        setupListeners();
        displayWelcomeMessage();
    }

    private void setupListeners() {
        binding.btnCategories.setOnClickListener(v -> openCategoriesPage());
        binding.btnStatistics.setOnClickListener(v -> openStatisticsPage());
        binding.btnTips.setOnClickListener(v -> openTipsPage());
    }

    private void displayWelcomeMessage() {
        String userName = prefsHelper.getUserFullName();
        if (userName.isEmpty()) {
            userName = getString(R.string.default_user_name);
        }
        binding.tvWelcome.setText(getString(R.string.welcome_message, userName));
    }

    private void openCategoriesPage() {
        startActivity(new Intent(this, CategoriesPageActivity.class));
    }

    private void openStatisticsPage() {
        startActivity(new Intent(this, StatisticsPageActivity.class));
    }

    private void openTipsPage() {
        startActivity(new Intent(this, TipsPageActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == R.id.action_clear_data) {
            showClearDataConfirmationDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showClearDataConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.clear_data_title)
                .setMessage(R.string.clear_data_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> clearAllData())
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void clearAllData() {
        prefsHelper.clearAllUserData();
        Toast.makeText(this, R.string.data_cleared, Toast.LENGTH_SHORT).show();
        logout();
    }

    private void logout() {
        prefsHelper.setUserLoggedIn(false);
        prefsHelper.clearUserSession();
        Intent intent = new Intent(this, LoginPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}