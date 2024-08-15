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
import com.machdevs.ecoreciclaappmobile.utils.SharedPreferencesHelper;

public class PrincipalPageActivity extends AppCompatActivity {

    private ActivityPrincipalPageBinding principalPageBinding;
    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        principalPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_principal_page);
        prefsHelper = new SharedPreferencesHelper(this);

        setSupportActionBar(principalPageBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("EcoRecicla");
        }

        setupListeners();
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
                .setTitle("Borrar datos")
                .setMessage("¿Estás seguro de que quieres borrar todos los datos de usuario? Esta acción no se puede deshacer.")
                .setPositiveButton("Sí", (dialog, which) -> clearAllData())
                .setNegativeButton("No", null)
                .show();
    }

    private void clearAllData() {
        prefsHelper.clearAllUserData();
        Toast.makeText(this, "Todos los datos han sido borrados", Toast.LENGTH_SHORT).show();
        // Redirigir al usuario a la pantalla de login
        Intent intent = new Intent(this, LoginPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void setupListeners() {
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

    private void logout() {
        prefsHelper.setUserLoggedIn(false);
        prefsHelper.clearUserSession();
        Intent intent = new Intent(PrincipalPageActivity.this, LoginPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}