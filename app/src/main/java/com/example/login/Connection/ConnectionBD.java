package com.example.login.Connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionBD extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;

    public ConnectionBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE USUARIO (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT NOT NULL, " +
                "clave TEXT NOT NULL)";
        db.execSQL(createTable);
        // Insertamos algunos datos de prueba
        db.execSQL("INSERT INTO USUARIO (usuario, clave) VALUES ('admin', '1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        onCreate(db);
    }

    public SQLiteDatabase openDatabase() {
        return this.getWritableDatabase();
    }
}
