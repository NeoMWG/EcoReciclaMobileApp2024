package com.machdevs.ecoreciclaappmobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.machdevs.ecoreciclaappmobile.models.RecyclingEntry;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {
    private static final String PREF_NAME = "EcoReciclaPrefs";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_LOGGED_IN = "user_logged_in";
    private static final String KEY_USER_FULL_NAME = "user_full_name";
    private static final String KEY_USER_PASSWORD = "user_password";
    private static final String KEY_RECYCLING_ENTRIES = "recycling_entries";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUserData(String fullName, String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_FULL_NAME, fullName);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_PASSWORD, password);
        editor.apply();
    }

    public boolean validateUserCredentials(String email, String password) {
        String savedEmail = sharedPreferences.getString(KEY_USER_EMAIL, "");
        String savedPassword = sharedPreferences.getString(KEY_USER_PASSWORD, "");
        return email.equals(savedEmail) && password.equals(savedPassword);
    }

    public void setUserLoggedIn(boolean loggedIn) {
        sharedPreferences.edit().putBoolean(KEY_USER_LOGGED_IN, loggedIn).apply();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_USER_LOGGED_IN, false);
    }

    public void clearUserSession() {
        sharedPreferences.edit().remove(KEY_USER_LOGGED_IN).apply();
    }

    public void clearAllUserData() {
        sharedPreferences.edit().clear().apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }

    public String getUserFullName() {
        return sharedPreferences.getString(KEY_USER_FULL_NAME, "");
    }

    public void saveRecyclingEntries(List<RecyclingEntry> entries) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
            String json = gson.toJson(entries);
        editor.putString(KEY_RECYCLING_ENTRIES, json);
        editor.apply();
    }

    public List<RecyclingEntry> getRecyclingEntries() {
        String json = sharedPreferences.getString(KEY_RECYCLING_ENTRIES, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<RecyclingEntry>>() {}.getType();
        return gson.fromJson(json, type);
    }
}