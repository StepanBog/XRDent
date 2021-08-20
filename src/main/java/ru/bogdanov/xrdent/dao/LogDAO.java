package ru.bogdanov.xrdent.dao;

import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
@Component
public class LogDAO {
    private Connection connection;
    private DB_Properties properties;
    private final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
    public LogDAO() {
        properties = new DB_Properties();
        try {
            connection = DriverManager.getConnection(properties.toString(),properties.getUsername(),properties.getPassword());
        }  catch (SQLException e){

        }
    }

    public boolean cheakToken(String token,String role){
        try {
            String query = "SELECT * FROM access_token WHERE Token = ? AND Role = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,token);
            statement.setString(2,role);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                String query1 = "UPDATE access_token SET Date_time = ? WHERE Token = ?";
                statement = connection.prepareStatement(query1);
                statement.setTimestamp(1,Timestamp.valueOf(properties.currentDataTime()));
                statement.setString(2,token);
                return true;
            } else return false;
        } catch (SQLException e){
            return false;
        }
    }

    public Pair<String, Long> docLogIN(String login, String password) {
        try {
            String query = "SELECT Doc_ID FROM doc_auth WHERE Login = ? AND Password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                   String token = generateNewToken();
                   String query1 = "INSERT INTO access_token(Token,Role,Date_time) VALUES(?,?,?)";
                   statement = connection.prepareStatement(query1);
                   statement.setString(1,token);
                   statement.setString(2,"doctor");
                   statement.setTimestamp(3,Timestamp.valueOf(properties.currentDataTime()));
                   statement.execute();
                   Pair<String,Long> pair = new Pair<>(token, result.getLong(1));
                   return pair;
            }
            return new Pair<String,Long>("",Integer.toUnsignedLong(0));
        }catch (SQLException e) {
            return new Pair<String,Long>("",Integer.toUnsignedLong(0));
        }

    }


    public Pair<String, Long> labLogIN(String login, String password) {
        try {
            String query = "SELECT Lab_ID FROM lab_auth WHERE Login = ? AND Password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                String token = generateNewToken();
                String query1 = "INSERT INTO access_token(Token,Role,Date_time) VALUES(?,?,?)";
                statement = connection.prepareStatement(query1);
                statement.setString(1,token);
                statement.setString(2,"laboratory");
                statement.setTimestamp(3,Timestamp.valueOf(properties.currentDataTime()));
                statement.execute();
                Pair<String,Long> pair = new Pair<>(token, result.getLong(1));
                return pair;
            }
            return new Pair<String,Long>("",Integer.toUnsignedLong(0));
        }catch (SQLException e) {
            return new Pair<String,Long>("",Integer.toUnsignedLong(0));
        }

    }
}
