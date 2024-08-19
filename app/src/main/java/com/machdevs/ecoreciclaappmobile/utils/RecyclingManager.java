package com.machdevs.ecoreciclaappmobile.utils;

import android.content.Context;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.models.RecyclingCategory;
import com.machdevs.ecoreciclaappmobile.models.RecyclingEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclingManager {
    private List<RecyclingCategory> categories;
    private List<RecyclingEntry> entries;
    private SharedPreferencesHelper prefsHelper;

    public RecyclingManager(Context context) {
        prefsHelper = new SharedPreferencesHelper(context);
        categories = new ArrayList<>();
        entries = prefsHelper.getRecyclingEntries();
        initializeCategories();
    }

    private void initializeCategories() {
        categories.add(new RecyclingCategory("Plástico", "kg", "Botellas, envases y otros productos plásticos", R.drawable.background_plastic));
        categories.add(new RecyclingCategory("Papel", "kg", "Periódicos, revistas, cajas de cartón", R.drawable.background_paper));
        categories.add(new RecyclingCategory("Vidrio", "kg", "Botellas y frascos de vidrio", R.drawable.background_glass));
        categories.add(new RecyclingCategory("Metal", "kg", "Latas, papel aluminio y otros metales", R.drawable.background_metal));
        categories.add(new RecyclingCategory("Orgánico", "kg", "Restos de comida y desechos de jardín", R.drawable.background_organic));
    }

    public List<RecyclingCategory> getCategories() {
        return categories;
    }

    public void addEntry(RecyclingEntry entry) {
        entries.add(entry);
        saveEntries();
    }

    public void updateEntry(RecyclingEntry entry) {
        int index = entries.indexOf(entry);
        if (index != -1) {
            entries.set(index, entry);
            saveEntries();
        }
    }

    public void deleteEntry(RecyclingEntry entry) {
        entries.remove(entry);
        saveEntries();
    }

    private void saveEntries() {
        prefsHelper.saveRecyclingEntries(entries);
    }

    public List<RecyclingEntry> getEntriesByCategory(RecyclingCategory category) {
        return entries.stream()
                .filter(entry -> entry.getCategory().getName().equals(category.getName()))
                .collect(Collectors.toList());
    }

    public double calculateAverageByCategory(RecyclingCategory category) {
        List<RecyclingEntry> categoryEntries = getEntriesByCategory(category);
        if (categoryEntries.isEmpty()) {
            return 0;
        }
        double sum = categoryEntries.stream().mapToDouble(RecyclingEntry::getAmount).sum();
        return sum / categoryEntries.size();
    }

    public double calculateMaxByCategory(RecyclingCategory category) {
        List<RecyclingEntry> categoryEntries = getEntriesByCategory(category);
        return categoryEntries.stream().mapToDouble(RecyclingEntry::getAmount).max().orElse(0);
    }

    public double calculateMinByCategory(RecyclingCategory category) {
        List<RecyclingEntry> categoryEntries = getEntriesByCategory(category);
        return categoryEntries.stream().mapToDouble(RecyclingEntry::getAmount).min().orElse(0);
    }

    public double calculateTotalValueByCategory(RecyclingCategory category) {
        List<RecyclingEntry> categoryEntries = getEntriesByCategory(category);
        return categoryEntries.stream().mapToDouble(RecyclingEntry::getValue).sum();
    }

    public double calculateTotalAmountByCategory(RecyclingCategory category) {
        List<RecyclingEntry> categoryEntries = getEntriesByCategory(category);
        return categoryEntries.stream().mapToDouble(RecyclingEntry::getAmount).sum();
    }

    public List<RecyclingEntry> getEntriesByDateRange(Date startDate, Date endDate) {
        return entries.stream()
                .filter(entry -> !entry.getDate().before(startDate) && !entry.getDate().after(endDate))
                .collect(Collectors.toList());
    }

    public RecyclingCategory getLeastRecycledCategory() {
        if (categories.isEmpty()) {
            return null;
        }
        return categories.stream()
                .min((c1, c2) -> Double.compare(calculateTotalAmountByCategory(c1), calculateTotalAmountByCategory(c2)))
                .orElse(null);
    }
}