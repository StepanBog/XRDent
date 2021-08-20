package ru.bogdanov.xrdent.dao;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Component
public class DB_Properties {
    private final String FILE = "/templates/jdbc.properties";
    protected String host;
    protected String port;
    protected String database;
    protected String username;
    protected String password;

    public String currentDataTime(){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = new java.util.Date();
        return  sdf.format(dt);
    }
    public DB_Properties() {
        InputStream inputStream = DB_Properties.class.getResourceAsStream(FILE);
        Properties properties =  new Properties();
        {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.host = properties.getProperty("jdbc.host");
        this.port = properties.getProperty("jdbc.port");;
        this.database = properties.getProperty("jdbc.database");;
        this.username = properties.getProperty("jdbc.username");;
        this.password = properties.getProperty("jdbc.password");;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return
                "jdbc:mysql://" + host +
                ":" +  port + '/'+ database;
    }
}
