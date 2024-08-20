package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.adapters.TipsAdapter;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityTipsPageBinding;
import com.machdevs.ecoreciclaappmobile.models.RecyclingCategory;
import com.machdevs.ecoreciclaappmobile.models.TipItem;
import com.machdevs.ecoreciclaappmobile.utils.RecyclingManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TipsPageActivity extends AppCompatActivity {

    private ActivityTipsPageBinding binding;
    private RecyclingManager recyclingManager;
    private TipsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tips_page);
        recyclingManager = new RecyclingManager(this);

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<TipItem> tipItems = generateTips();
        Collections.shuffle(tipItems);
        adapter = new TipsAdapter(tipItems);
        binding.recyclerViewTips.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewTips.setAdapter(adapter);
    }

    private List<TipItem> generateTips() {
        List<TipItem> tips = new ArrayList<>();

        RecyclingCategory leastRecycledCategory = recyclingManager.getLeastRecycledCategory();
        if (leastRecycledCategory != null) {
            tips.add(new TipItem("Mejora tu reciclaje de " + leastRecycledCategory.getName(),
                    "Intenta aumentar tu reciclaje de " + leastRecycledCategory.getName() + ". Cada pequeño esfuerzo cuenta."));
        }

        for (RecyclingCategory category : recyclingManager.getCategories()) {
            double averageAmount = recyclingManager.calculateAverageByCategory(category);
            if (averageAmount < 5) {
                tips.add(new TipItem("Aumenta tu reciclaje de " + category.getName(),
                        "Tu promedio de reciclaje de " + category.getName() + " es bajo. Intenta identificar más items que puedas reciclar en esta categoría."));
            } else if (averageAmount > 20) {
                tips.add(new TipItem("¡Excelente trabajo con " + category.getName() + "!",
                        "Estás haciendo un gran trabajo reciclando " + category.getName() + ". ¡Sigue así!"));
            }
        }

        tips.add(new TipItem("Separación de residuos",
                "Separa tus residuos en diferentes contenedores: orgánicos, papel y cartón, plásticos, vidrio y metal."));
        tips.add(new TipItem("Reducir el consumo",
                "Intenta reducir el consumo de productos de un solo uso y opta por alternativas reutilizables."));
        tips.add(new TipItem("Compra a granel",
                "Compra productos a granel para reducir el embalaje innecesario."));
        tips.add(new TipItem("Reutiliza",
                "Busca formas creativas de reutilizar objetos antes de desecharlos."));
        tips.add(new TipItem("Compostaje",
                "Si tienes espacio, considera hacer compostaje con tus residuos orgánicos."));
        tips.add(new TipItem("Educación",
                "Educa a tu familia y amigos sobre la importancia del reciclaje y cómo hacerlo correctamente."));
        tips.add(new TipItem("Compra responsable",
                "Elige productos con embalajes reciclables o biodegradables cuando sea posible."));
        tips.add(new TipItem("Donación",
                "Dona artículos que ya no uses en lugar de tirarlos, si están en buen estado."));

        return tips;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}