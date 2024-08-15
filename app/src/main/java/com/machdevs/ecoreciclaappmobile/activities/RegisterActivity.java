package com.machdevs.ecoreciclaappmobile.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityRegisterBinding;
import com.machdevs.ecoreciclaappmobile.utils.SharedPreferencesHelper;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;
    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        prefsHelper = new SharedPreferencesHelper(this);

        setupListeners();
    }

    private void setupListeners() {
        registerBinding.btnRegister.setOnClickListener(v -> onRegisterButtonClick());
        registerBinding.tvGoToLogin.setOnClickListener(v -> onLoginTextClick());
    }

    private void onRegisterButtonClick() {
        String fullName = registerBinding.etFullName.getText().toString().trim();
        String email = registerBinding.etEmail.getText().toString().trim();
        String password = registerBinding.etPassword.getText().toString();
        String confirmPassword = registerBinding.etConfirmPassword.getText().toString();

        if (validateForm(fullName, email, password, confirmPassword)) {
            prefsHelper.saveUserData(fullName, email, password);
            Toast.makeText(this, "Registro exitoso. Por favor, inicie sesión.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private boolean validateForm(String fullName, String email, String password, String confirmPassword) {
        boolean isValid = true;

        if (fullName.isEmpty()) {
            registerBinding.etFullName.setError("El nombre completo es requerido");
            isValid = false;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerBinding.etEmail.setError("Email inválido");
            isValid = false;
        }

        if (password.length() < 6) {
            registerBinding.etPassword.setError("La contraseña debe tener al menos 6 caracteres");
            isValid = false;
        }

        if (!password.equals(confirmPassword)) {
            registerBinding.etConfirmPassword.setError("Las contraseñas no coinciden");
            isValid = false;
        }

        return isValid;
    }

    private void onLoginTextClick() {
        finish();
    }
}