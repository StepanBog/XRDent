package ru.bogdanov.xrdent.dao;

import ru.bogdanov.xrdent.entity.*;
import ru.bogdanov.xrdent.entity.direction.RegisteredDirection;
import ru.bogdanov.xrdent.entity.result.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryDAO {
    private Connection connection;
    private DB_Properties properties;
    public LaboratoryDAO() {
        properties = new DB_Properties();
        try {
            connection = DriverManager.getConnection(properties.toString(),properties.getUsername(),properties.getPassword());
        }  catch (SQLException e){

        }
    }

    public void postResult(Result res){
    }

    public Laboratory getLabByID(Long id) {
        try {
            String query = "SELECT * FROM laboratory WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            List<RegisteredDirection> l = new ArrayList<>();
            while (result.next()){
                Laboratory lab = new Laboratory();
                lab.setId(result.getLong(1));
                lab.setName(result.getString(2));
                lab.setAdress(result.getString(3));
                return lab;
            }
           return null;
        } catch (SQLException e){
            return null;
        }
    }

}
