package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.login.Connection.ConnectionBD;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, clave;
    TextView lblregistrar;
    Button btningresar;
    ConnectionBD dbHelper; // Cambiado a usar ConnectionBD como SQLiteOpenHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = findViewById(R.id.txtusuario);
        clave = findViewById(R.id.txtclave);
        lblregistrar = findViewById(R.id.lblregistrar);
        btningresar = findViewById(R.id.btningresar);

        dbHelper = new ConnectionBD(this); // Inicializar el helper de SQLite

        btningresar.setOnClickListener(v -> new LoginTask().execute());

        lblregistrar.setOnClickListener(v -> {
            Intent reg = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(reg);
        });
    }

    // Clase AsyncTask ajustada para manejar SQLite
    public class LoginTask extends AsyncTask<Void, Void, Boolean> {
        String mensaje = "";

        @Override
        protected Boolean doInBackground(Void... voids) {
            SQLiteDatabase db = dbHelper.openDatabase();
            boolean exito = false;

            try {
                String query = "SELECT * FROM USUARIO WHERE usuario = ? AND clave = ?";
                Cursor cursor = db.rawQuery(query, new String[]{usuario.getText().toString(), clave.getText().toString()});

                if (cursor.moveToFirst()) {
                    exito = true;
                    mensaje = "Acceso exitoso";
                } else {
                    mensaje = "Acceso denegado";
                }

                cursor.close();
            } catch (Exception e) {
                Log.e("Error de conexion", e.getMessage());
                mensaje = "Error en la autenticación";
            } finally {
                db.close();
            }

            return exito;
        }

        @Override
        protected void onPostExecute(Boolean exito) {
            Toast.makeText(LoginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            if (exito) {
                Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menu);

                // Limpiar campos
                usuario.setText("");
                clave.setText("");
            }
        }
    }
}
