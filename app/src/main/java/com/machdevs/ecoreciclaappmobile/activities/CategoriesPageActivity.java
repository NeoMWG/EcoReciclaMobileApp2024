package com.machdevs.ecoreciclaappmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.adapters.CategoryAdapter;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityCategoriesPageBinding;
import com.machdevs.ecoreciclaappmobile.models.RecyclingCategory;
import com.machdevs.ecoreciclaappmobile.utils.RecyclingManager;
import java.util.List;

public class CategoriesPageActivity extends AppCompatActivity {

    private ActivityCategoriesPageBinding binding;
    private RecyclingManager recyclingManager;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_categories_page);
        recyclingManager = new RecyclingManager(this);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<RecyclingCategory> categories = recyclingManager.getCategories();
        adapter = new CategoryAdapter(categories, this::openRecyclingEntryPage);
        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCategories.setAdapter(adapter);
    }

    private void openRecyclingEntryPage(RecyclingCategory category) {
        Intent intent = new Intent(CategoriesPageActivity.this, RecyclingEntryActivity.class);
        intent.putExtra("SELECTED_CATEGORY", category);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}