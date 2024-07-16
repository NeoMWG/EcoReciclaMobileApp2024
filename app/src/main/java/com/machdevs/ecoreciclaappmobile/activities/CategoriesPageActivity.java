package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityCategoriesPageBinding;

import java.util.Arrays;
import java.util.List;

public class CategoriesPageActivity extends AppCompatActivity {

    private ActivityCategoriesPageBinding categoriesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesBinding = DataBindingUtil.setContentView(this, R.layout.activity_categories_page);

        List<String> categories = Arrays.asList(
                "Plástico",
                "Papel y Cartón",
                "Vidrio",
                "Metales",
                "Orgánicos"
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);

        categoriesBinding.listViewCategories.setAdapter(adapter);
    }
}