package com.example.trabajosnicaragua;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import data.DBHelper;
import data.PostgreSQLHelper;
import es.dmoral.toasty.Toasty;
import models.Usuario;

public class LoginActivity extends AppCompatActivity  {

    private static final String CORRECT_USERNAME = "";
    private static final String CORRECT_PASSWORD = "";
    private static final String TAG = "MainActivity";
    private TextView textNameAppView;
    private final String fullTextNameApp = "Trabajos Nicaragua.";
    private final Handler handler = new Handler();
    private int currentIndex = 0;
    private EditText editTextUsername;
    private EditText editTextPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        textNameAppView = findViewById(R.id.textNameApp);

        startTypingAnimation();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userID = sessionManagement.getSession();

        if(userID != -1){
            //user id logged in and so move to mainActivity
            DBHelper dbHelper = new DBHelper();
            Usuario usuarioYaLogeado = dbHelper.getUsuarioById(userID);
            moveToMainActivity(usuarioYaLogeado);
        }
        else{
            //do nothing
        }
    }
    private void moveToMainActivity(Usuario usuario) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user_rol", usuario.getRol()); // Pass the variable
        intent.putExtra("user_id", usuario.getId()); // Pass the variable
        startActivity(intent);
    }

    private void startTypingAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (currentIndex <= fullTextNameApp.length()) {
                        textNameAppView.setText(fullTextNameApp.substring(0, currentIndex));
                        currentIndex++;
                        handler.postDelayed(this, 125); // Adjust typing speed here
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startDeletingAnimation();
                            }
                        }, 3000); // Pause for 3 seconds
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error in typing animation", e);
                }
            }
        }, 125);
    }

    private void startDeletingAnimation() {
        currentIndex -= 1;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {

                    if (currentIndex >= 0) {
                        textNameAppView.setText(fullTextNameApp.substring(0, currentIndex));
                        currentIndex--;
                        handler.postDelayed(this, 125); // Adjust deleting speed here
                    } else {
                        currentIndex = 0;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startTypingAnimation();
                            }
                        }, 5000); // Pause before starting the typing animation again
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error in deleting animation", e);
                }
            }
        }, 125);
    }

    private void login() {
        String email = editTextUsername.getText().toString().toLowerCase();
        String password = editTextPassword.getText().toString();

        DBHelper dbHelper = new DBHelper();
        int exist = 0;

        List<Usuario> usuarios = dbHelper.getAllUsuarios();
        Usuario usuario_logeado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().toString().toLowerCase().equals(email) && usuario.getContraseña().toString().equals(password)) {
                exist = 1;
                usuario_logeado = usuario;
                //1. login and save session
                SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                sessionManagement.saveSession(usuario_logeado);
                break;
            } else {
                exist = 0;
            }
        }

        if (email.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            Toasty.success(this, "¡Bienvenido!", Toast.LENGTH_SHORT, true).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (exist == 1) {
            Toasty.success(this, "¡Bienvenido!", Toast.LENGTH_SHORT, true).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("user_rol", usuario_logeado.getRol()); // Pass the variable
            intent.putExtra("user_id", usuario_logeado.getId()); // Pass the variable
            startActivity(intent);
            exist = 0;
            finish();
        } else if (exist == 0){
            Toasty.error(this, "¡Credenciales incorrectos!", Toast.LENGTH_SHORT, true).show();

        }


    }
    private void register() {
        Intent intent = new Intent(this, AddUsuarioActivity.class);
        startActivity(intent);
        finish();
    }


}