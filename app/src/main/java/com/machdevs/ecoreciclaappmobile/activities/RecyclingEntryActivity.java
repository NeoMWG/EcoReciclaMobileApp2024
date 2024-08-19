package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityRecyclingEntryBinding;
import com.machdevs.ecoreciclaappmobile.models.RecyclingCategory;
import com.machdevs.ecoreciclaappmobile.models.RecyclingEntry;
import com.machdevs.ecoreciclaappmobile.utils.RecyclingManager;
import java.util.Date;

public class RecyclingEntryActivity extends AppCompatActivity {

    private ActivityRecyclingEntryBinding binding;
    private RecyclingManager recyclingManager;
    private RecyclingCategory selectedCategory;
    private RecyclingEntry currentEntry;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycling_entry);

        recyclingManager = new RecyclingManager(this);
        selectedCategory = (RecyclingCategory) getIntent().getSerializableExtra("SELECTED_CATEGORY");
        currentEntry = (RecyclingEntry) getIntent().getSerializableExtra("CURRENT_ENTRY");

        if (selectedCategory == null && currentEntry == null) {
            Toast.makeText(this, "Error: No se seleccionó una categoría o entrada", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (currentEntry != null) {
            isEditMode = true;
            selectedCategory = currentEntry.getCategory();
        }

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setupUI();
        setupListeners();
    }

    private void setupUI() {
        binding.tvCategoryName.setText(selectedCategory.getName());
        binding.tvUnit.setText(selectedCategory.getUnit());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(isEditMode ? "Editar Entrada" : "Nueva Entrada");
        }

        if (isEditMode) {
            binding.etAmount.setText(String.valueOf(currentEntry.getAmount()));
            binding.etValue.setText(String.valueOf(currentEntry.getValue()));
            binding.btnSaveEntry.setText(R.string.actualizar);
            binding.btnDelete.setVisibility(View.VISIBLE);
        } else {
            binding.btnDelete.setVisibility(View.GONE);
        }
    }

    private void setupListeners() {
        binding.btnSaveEntry.setOnClickListener(v -> saveEntry());
        binding.btnDelete.setOnClickListener(v -> showDeleteConfirmationDialog());
    }

    private void saveEntry() {
        String amountStr = binding.etAmount.getText().toString();
        String valueStr = binding.etValue.getText().toString();

        if (amountStr.isEmpty() || valueStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            double value = Double.parseDouble(valueStr);
            Date currentDate = new Date();

            if (isEditMode) {
                currentEntry.setAmount(amount);
                currentEntry.setValue(value);
                currentEntry.setDate(currentDate);
                recyclingManager.updateEntry(currentEntry);
                Toast.makeText(this, "Entrada actualizada exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                RecyclingEntry newEntry = new RecyclingEntry(selectedCategory, amount, value, currentDate);
                recyclingManager.addEntry(newEntry);
                Toast.makeText(this, "Entrada guardada exitosamente", Toast.LENGTH_SHORT).show();
            }
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa valores numéricos válidos", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar entrada")
                .setMessage("¿Estás seguro de que quieres eliminar esta entrada?")
                .setPositiveButton("Sí", (dialog, which) -> deleteEntry())
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteEntry() {
        if (currentEntry != null) {
            recyclingManager.deleteEntry(currentEntry);
            Toast.makeText(this, "Entrada eliminada exitosamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}