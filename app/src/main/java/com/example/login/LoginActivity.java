package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;

public class LoginActivity extends AppCompatActivity {

 EditText usuario, clave;
 TextView lblregistrar;
 Button btnregistrar;
 Connection con;

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
        usuario = (EditText) findViewById(R.id.txtusuario);
        clave = (EditText) findViewById(R.id.txtclave);
        lblregistrar = (TextView) findViewById(R.id.lblregistrar);
        btnregistrar = (Button) findViewById(R.id.btningresar);

    }
}