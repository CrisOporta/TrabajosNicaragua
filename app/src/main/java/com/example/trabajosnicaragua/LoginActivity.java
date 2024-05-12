package com.example.trabajosnicaragua;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import models.Usuario;

public class LoginActivity extends AppCompatActivity  {

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "admin";

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper dbHelper = new DBHelper(this);

        List<Usuario> usuarios = dbHelper.getAllUsuarios();

// Ahora puedes trabajar con la lista de usuarios, por ejemplo, imprimirlos en el log:
        for (Usuario usuario : usuarios) {
            Log.d("Usuario", "ID: " + usuario.getId() +
                    ", Nombre: " + usuario.getNombre() +
                    ", Apellido: " + usuario.getApellido() +
                    ", Email: " + usuario.getEmail() +
                    ", Contraseña: " + usuario.getContraseña() +
                    ", Rol: " + usuario.getRol() +
                    ", Verificado: " + usuario.getVerificado());
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
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
    private void register() {
        Intent intent = new Intent(this, AddUsuarioActivity.class);
        startActivity(intent);
        finish();
    }
}