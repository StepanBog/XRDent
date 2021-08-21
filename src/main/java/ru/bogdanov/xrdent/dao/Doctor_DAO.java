package ru.bogdanov.xrdent.dao;

import ru.bogdanov.xrdent.entity.direction.Direction;
import ru.bogdanov.xrdent.entity.Doctor;
import org.springframework.stereotype.Component;

import java.sql.*;
@Component
public class Doctor_DAO {
    private Connection connection;
    private DB_Properties properties;
    public Doctor_DAO() {
        properties = new DB_Properties();
        try {
            connection = DriverManager.getConnection(properties.toString(),properties.getUsername(),properties.getPassword());
        }  catch (SQLException e){

        }
    }
    public void postDirection(Direction d) {
        try {
            String query = "INSERT INTO direction(doctor_id, patient_id, description, data_time) VALUE(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,d.getDoctor_id());
            statement.setLong(2,d.getPatient_id());
            statement.setString(3,d.getDescription());
            statement.setTimestamp(4,d.getDataTime());
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public Doctor getByID(Long id) {
        try {
            String query = "SELECT ID,Name,Surname FROM doctor WHERE ID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Doctor d = new Doctor(Integer.parseInt(result.getString(1)), result.getString(2), result.getString(3));
                return d;
            }
            return null;
        }catch (SQLException e) {
            return null;
        }
    }
}
