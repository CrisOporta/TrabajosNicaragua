package com.example.trabajosnicaragua;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class AddUsuarioActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellido, editTextEmail, editTextContraseña, editTextConfirmarContraseña;
    private Spinner editSpinnerRol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addusuario);

        editTextNombre = findViewById(R.id.user_name);
        editTextApellido = findViewById(R.id.user_lastname);
        editTextEmail = findViewById(R.id.user_email);
        editTextContraseña = findViewById(R.id.user_password);
        editTextConfirmarContraseña = findViewById(R.id.confirm_user_password);
        editSpinnerRol = findViewById(R.id.spinner1);


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Postulante");
        spinnerArray.add("Reclutador");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner1);
        sItems.setAdapter(adapter);


        Button btnAgregar = findViewById(R.id.buttonRegister);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsuario();
            }
        });
    }

    private void addUsuario() {
        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String email = editTextEmail.getText().toString().trim();
        String contraseña = editTextContraseña.getText().toString();
        String confirmarContraseña = editTextConfirmarContraseña.getText().toString();
        String rol = editSpinnerRol.getSelectedItem().toString().toLowerCase();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty() || rol.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contraseña.toString().trim().equals(confirmarContraseña.toString().trim())) {
            Toast.makeText(this, "¡La contraseña no coincide!", Toast.LENGTH_SHORT).show();
            return;
        }

        DBHelper dbHelper = new DBHelper(this);

        int countUsuarios = dbHelper.getUsuariosCount() + 1;


        // Crear un objeto Usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(countUsuarios, nombre, apellido, email, contraseña, rol, 0); // Verificado inicialmente como 0


        if (!dbHelper.addUsuario(nuevoUsuario)) {
            Toast toast = Toast.makeText(this, "¡Error al agregar el usuario!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "¡Usuario agregado exitosamente!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

    }
}
