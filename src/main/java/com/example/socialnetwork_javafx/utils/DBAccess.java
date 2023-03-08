package com.example.socialnetwork_javafx.utils;

import com.example.socialnetwork_javafx.HelloApplication;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBAccess {
    private String url;
    private String user;
    private String password;

    public DBAccess() {
        loadDBCredentials();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error getting connection " + e);
        }

        return connection;
    }

    private void loadDBCredentials() {
        Properties properties = new Properties();

        try (InputStream input = HelloApplication.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(input);

            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
