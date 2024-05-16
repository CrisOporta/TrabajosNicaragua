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

    DBHelper dbHelper = new DBHelper(this);

    int exist = 0;

    private EditText editTextUsername;
    private EditText editTextPassword;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//
//
//        editTextUsername = findViewById(R.id.editTextUsername);
//        editTextPassword = findViewById(R.id.editTextPassword);
//
//        Button buttonLogin = findViewById(R.id.buttonLogin);
//        Button buttonRegister = findViewById(R.id.buttonRegister);
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
//        buttonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                register();
//            }
//        });
//    }

    private void login() {
        String email = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        List<Usuario> usuarios = dbHelper.getAllUsuarios();


        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().toString().equals(email) && usuario.getContraseña().toString().equals(password)) {
                exist = 1;
            } else {
                exist = 0;
            }
        }

        if (email.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            Toast toast = Toast.makeText(this, "¡Credenciales correctos!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (exist == 1) {
            Toast toast = Toast.makeText(this, "¡Credenciales correctos!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            exist = 0;
            finish();
        } else if (exist == 0){
            Toast.makeText(this, "¡Credenciales incorrectos!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
    private void register() {
        Intent intent = new Intent(this, AddUsuarioActivity.class);
        startActivity(intent);
        finish();
    }
}