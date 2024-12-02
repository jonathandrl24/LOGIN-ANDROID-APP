package com.example.login.Connection;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private final String ip = "192.168.68.105"; // Cambia a la IP correcta de tu base de datos
    private final String usuario = "root";
    private final String password = "";
    private final String basedatos = "login";

    @SuppressLint("NewApi")
    public Connection connect() {
        Connection connection = null;
        String connectionUrl;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionUrl = "jdbc:mysql://" + ip + ":3306/" + basedatos + "?useSSL=false&serverTimezone=UTC";

            connection = DriverManager.getConnection(connectionUrl, usuario, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase del driver no encontrada. Verifica que agregaste el conector MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Fallo en la conexión con la base de datos. Verifica los detalles de conexión.");
            e.printStackTrace();
        }
        return connection;
    }
}

