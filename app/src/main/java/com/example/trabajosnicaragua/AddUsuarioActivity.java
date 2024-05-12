package com.example.trabajosnicaragua;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddUsuarioActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellido, editTextEmail, editTextContraseña, editTextRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addusuario);

        editTextNombre = findViewById(R.id.user_name);
        editTextApellido = findViewById(R.id.user_lastname);
        editTextEmail = findViewById(R.id.user_email);
        editTextContraseña = findViewById(R.id.user_password);
        

        Button btnAgregar = findViewById(R.id.buttonRegisterUser);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarUsuario();
            }
        });
    }

    private void insertarUsuario() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nombre = editTextNombre.getText().toString().trim();
        String apellido = editTextApellido.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String contraseña = editTextContraseña.getText().toString().trim();
        String rol = editTextRol.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("email", email);
        values.put("contraseña", contraseña);
        values.put("rol", rol);

        long newRowId = db.insert("Usuarios", null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error al agregar el usuario", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
            // Puedes añadir aquí un código para ir a otra actividad o realizar otra acción después de agregar el usuario
        }
    }
}
