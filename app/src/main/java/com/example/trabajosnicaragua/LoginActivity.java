package com.example.trabajosnicaragua;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity  {

    private static final String CORRECT_USERNAME = "";
    private static final String CORRECT_PASSWORD = "";

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            Toast toast = Toast.makeText(this, "¡Credenciales correctos!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "¡Credenciales incorrectos!", Toast.LENGTH_SHORT).show();
        }
    }
}