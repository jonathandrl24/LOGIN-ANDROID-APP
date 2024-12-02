package com.example.login.Connection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import net.sourceforge.jtds.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private final String ip = "192.168.1.4";
    private final String usuario = "root";
    private final String password = "";
    private final String basedatos = "login";

    @SuppressLint("NewApi")
    public Connection connect() {
        Connection connection = null;
        String connectionUrl = null;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionUrl = "jdbc:jtds:sqlserver://" + this.ip + "/" + this.basedatos + ";user=" + this.usuario + ";password=" + this.password + ";";
            connection = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error de conexion SQL", e.getMessage());
        }
        return connection;
    }
}

