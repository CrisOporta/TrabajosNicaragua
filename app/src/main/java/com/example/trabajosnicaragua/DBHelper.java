package com.example.trabajosnicaragua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.Usuario;

public class DBHelper extends SQLiteOpenHelper {
    // Nombre de la base de datos
    private static final String DATABASE_NAME = "trabajosNicaragua.db";
    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear las tablas
        db.execSQL("CREATE TABLE Usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "contraseña TEXT NOT NULL," +
                "rol TEXT CHECK(rol IN ('reclutador', 'postulante')) NOT NULL," +
                "verificado INTEGER DEFAULT 0)");

        db.execSQL("CREATE TABLE Empleos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "ubicacion TEXT NOT NULL," +
                "salario REAL NOT NULL," +
                "requisitos TEXT NOT NULL," +
                "reclutador_id INTEGER NOT NULL," +
                "FOREIGN KEY (reclutador_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE Documentos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "archivo BLOB NOT NULL," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE Favoritos (" +
                "usuario_id INTEGER NOT NULL," +
                "empleo_id INTEGER NOT NULL," +
                "PRIMARY KEY (usuario_id, empleo_id)," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)," +
                "FOREIGN KEY (empleo_id) REFERENCES Empleos(id))");

        db.execSQL("CREATE TABLE HistorialBusqueda (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "terminos_busqueda TEXT," +
                "filtros_busqueda TEXT," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE Entrevistas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "empleo_id INTEGER NOT NULL," +
                "fecha TEXT NOT NULL," +
                "hora TEXT NOT NULL," +
                "ubicacion TEXT," +
                "estado TEXT DEFAULT 'Pendiente'," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)," +
                "FOREIGN KEY (empleo_id) REFERENCES Empleos(id))");

        db.execSQL("CREATE TABLE Asesoramiento (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "consulta TEXT NOT NULL," +
                "respuesta TEXT," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE PerfilesVerificados (" +
                "usuario_id INTEGER PRIMARY KEY," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE AlertasEmpleo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "terminos_busqueda TEXT," +
                "filtros_busqueda TEXT," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE Anomalias (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "captura_pantalla BLOB," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id))");

        db.execSQL("CREATE TABLE Idiomas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo TEXT NOT NULL UNIQUE)");

        db.execSQL("CREATE TABLE Traducciones (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idioma_id INTEGER NOT NULL," +
                "etiqueta TEXT NOT NULL," +
                "texto TEXT NOT NULL," +
                "FOREIGN KEY (idioma_id) REFERENCES Idiomas(id))");

        db.execSQL("CREATE TABLE CategoriasEmpleo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        db.execSQL("CREATE TABLE EmpleosCategorias (" +
                "empleo_id INTEGER NOT NULL," +
                "categoria_id INTEGER NOT NULL," +
                "PRIMARY KEY (empleo_id, categoria_id)," +
                "FOREIGN KEY (empleo_id) REFERENCES Empleos(id)," +
                "FOREIGN KEY (categoria_id) REFERENCES CategoriasEmpleo(id))");

        db.execSQL("CREATE TABLE PreguntasHabilidades (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pregunta TEXT NOT NULL)");

        db.execSQL("CREATE TABLE RespuestasHabilidades (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario_id INTEGER NOT NULL," +
                "pregunta_id INTEGER NOT NULL," +
                "respuesta TEXT NOT NULL," +
                "FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)," +
                "FOREIGN KEY (pregunta_id) REFERENCES PreguntasHabilidades(id))");

        db.execSQL("CREATE TABLE ChatBots (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pregunta TEXT NOT NULL," +
                "respuesta TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borrar las tablas si ya existen y crearlas de nuevo
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Empleos");
        db.execSQL("DROP TABLE IF EXISTS Documentos");
        db.execSQL("DROP TABLE IF EXISTS Favoritos");
        db.execSQL("DROP TABLE IF EXISTS HistorialBusqueda");
        db.execSQL("DROP TABLE IF EXISTS Entrevistas");
        db.execSQL("DROP TABLE IF EXISTS Asesoramiento");
        db.execSQL("DROP TABLE IF EXISTS PerfilesVerificados");
        db.execSQL("DROP TABLE IF EXISTS AlertasEmpleo");
        db.execSQL("DROP TABLE IF EXISTS Anomalias");
        db.execSQL("DROP TABLE IF EXISTS Idiomas");
        db.execSQL("DROP TABLE IF EXISTS Traducciones");
        db.execSQL("DROP TABLE IF EXISTS CategoriasEmpleo");
        db.execSQL("DROP TABLE IF EXISTS EmpleosCategorias");
        db.execSQL("DROP TABLE IF EXISTS PreguntasHabilidades");
        db.execSQL("DROP TABLE IF EXISTS RespuestasHabilidades");
        db.execSQL("DROP TABLE IF EXISTS ChatBots");

        // Crear las tablas de nuevo
        onCreate(db);
    }

    //---------------------------------------------
    // CRUD Usuario
    //---------------------------------------------
    // Create Usuario
    public void addUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("email", usuario.getEmail());
        values.put("contraseña", usuario.getContraseña());
        values.put("rol", usuario.getRol());
        values.put("verificado", usuario.getVerificado());
        db.insert("Usuarios", null, values);
        db.close();
    }
    // Read Usuario
    public Usuario getUsuarioById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Usuarios", new String[]{"id", "nombre", "apellido",
                        "email", "contraseña", "rol", "verificado"}, "id" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        Usuario usuario = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int nombreIndex = cursor.getColumnIndex("nombre");
            int apellidoIndex = cursor.getColumnIndex("apellido");
            int emailIndex = cursor.getColumnIndex("email");
            int contraseñaIndex = cursor.getColumnIndex("contraseña");
            int rolIndex = cursor.getColumnIndex("rol");
            int verificadoIndex = cursor.getColumnIndex("verificado");

            usuario = new Usuario(
                    cursor.getInt(idIndex),
                    cursor.getString(nombreIndex),
                    cursor.getString(apellidoIndex),
                    cursor.getString(emailIndex),
                    cursor.getString(contraseñaIndex),
                    cursor.getString(rolIndex),
                    cursor.getInt(verificadoIndex));
        }
        if (cursor != null) {
            cursor.close();
        }
        return usuario;
    }
    // Update Usuario
    public int updateUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("email", usuario.getEmail());
        values.put("contraseña", usuario.getContraseña());
        values.put("rol", usuario.getRol());
        values.put("verificado", usuario.getVerificado());
        return db.update("Usuarios", values, "id" + " = ?",
                new String[]{String.valueOf(usuario.getId())});
    }
    // Delete Usuario
    public void deleteUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Usuarios", "id" + " = ?",
                new String[]{String.valueOf(usuario.getId())});
        db.close();
    }



}
