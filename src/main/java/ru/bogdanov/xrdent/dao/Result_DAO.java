package ru.bogdanov.xrdent.dao;

import ru.bogdanov.xrdent.entity.direction.Direction_Cutted;
import ru.bogdanov.xrdent.entity.direction.RegisteredDirection;
import ru.bogdanov.xrdent.entity.result.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Result_DAO {
    private Connection connection;
    private DB_Properties properties;
    public Result_DAO() {
        properties = new DB_Properties();
        try {
            connection = DriverManager.getConnection(properties.toString(),properties.getUsername(),properties.getPassword());
        }  catch (SQLException e){

        }
    }


    public void post_result(Result result) {
        try {
            String query = "INSERT INTO result(id, data_src, description, direction_id) VALUE (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,result.getId());
            statement.setString(2,result.getDataSrc());
            statement.setString(3,result.getDescription());
            statement.setLong(4,result.getDirectionId());
            statement.execute();
        } catch (SQLException e){

        }
    }
    public List<Direction_Cutted> getListOfRegisteredDirection(Long lab_id){
        try {
            String query = "SELECT patient_name, patient_surname, phone_number, direction_id FROM registered_direction WHERE laboratory_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,lab_id);
            ResultSet result = statement.executeQuery();
            List<Direction_Cutted> l = new ArrayList<>();
            while (result.next()){
                Direction_Cutted d = new Direction_Cutted();
                d.setName(result.getString(1));
                d.setSurname(result.getString(2));
                d.setPhone_number(result.getString(3));
                d.setId(result.getLong(4));
                l.add(d);
            }
            return l;
        } catch (SQLException e){
            return null;
        }
    }
}
