package com.machdevs.ecoreciclaappmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityLoginBinding;
import com.machdevs.ecoreciclaappmobile.utils.SharedPreferencesHelper;

public class LoginPageActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        prefsHelper = new SharedPreferencesHelper(this);

        setupListeners();
        checkLoggedInState();
    }

    private void setupListeners() {
        loginBinding.btnLogin.setOnClickListener(v -> onLoginButtonClick());
        loginBinding.tvGoToRegister.setOnClickListener(v -> onRegisterButtonClick());
    }

    private void checkLoggedInState() {
        if (prefsHelper.isUserLoggedIn()) {
            navigateToPrincipalPage();
        }
    }

    private void onLoginButtonClick() {
        String email = loginBinding.etEmail.getText().toString().trim();
        String password = loginBinding.etPassword.getText().toString();

        if (validateForm(email, password)) {
            if (prefsHelper.validateUserCredentials(email, password)) {
                prefsHelper.setUserLoggedIn(true);
                navigateToPrincipalPage();
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateForm(String email, String password) {
        boolean isValid = true;

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginBinding.etEmail.setError("Email inválido");
            isValid = false;
        }

        if (password.isEmpty()) {
            loginBinding.etPassword.setError("La contraseña es requerida");
            isValid = false;
        }

        return isValid;
    }

    private void onRegisterButtonClick() {
        Intent intent = new Intent(LoginPageActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void navigateToPrincipalPage() {
        Intent intent = new Intent(LoginPageActivity.this, PrincipalPageActivity.class);
        startActivity(intent);
        finish();
    }
}