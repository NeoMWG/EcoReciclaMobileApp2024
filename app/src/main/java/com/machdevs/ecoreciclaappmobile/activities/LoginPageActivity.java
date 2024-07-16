package com.machdevs.ecoreciclaappmobile.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.machdevs.ecoreciclaappmobile.R;
import com.machdevs.ecoreciclaappmobile.databinding.ActivityLoginBinding;

public class LoginPageActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupListeners();
    }

    private void setupListeners() {
        loginBinding.btnLogin.setOnClickListener(v -> onLoginButtonClick());
        loginBinding.tvGoToRegister.setOnClickListener(v -> onRegisterButtonClick());
    }

    private void onRegisterButtonClick() {
        navigatorToRegisterPage();
    }

    private void onLoginButtonClick(){
        navigatorToPrincipalPage();
    }

    private void navigatorToPrincipalPage() {
        Intent intent = new Intent(LoginPageActivity.this, PrincipalPageActivity.class);
        startActivity(intent);
    }

    private void navigatorToRegisterPage() {
        Intent intent = new Intent(LoginPageActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}