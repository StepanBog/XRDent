package ru.bogdanov.xrdent.dao;

import ru.bogdanov.xrdent.entity.Patient;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Patient_DAO {
    private Connection connection;
    private DB_Properties properties;
    public Patient_DAO() {
        properties = new DB_Properties();
        try {
            connection = DriverManager.getConnection(properties.toString(),properties.getUsername(),properties.getPassword());
        }  catch (SQLException e){

        }
    }

    public void addPatient(Patient p) {
        try {
            String query = "INSERT INTO patient(name, surname, doctor_id, phone_number, age,description) VALUE(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,p.getName());
            statement.setString(2,p.getSurname());
            statement.setLong(3,p.getDoctorId());
            statement.setString(4,p.getPhoneNumber());
            statement.setInt(5,p.getAge());
            statement.setString(6,p.getDescription());
            statement.execute();
        } catch (SQLException e) {

        }
    }
    public List<Patient> getListByDocID(long id) {
        try {
        String query = "SELECT * FROM patient WHERE Doctor_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1,id);
        ResultSet result = statement.executeQuery();
        List<Patient> l = new ArrayList<>();
        while (result.next()){
            Patient p = new Patient();
            p.setId(result.getLong(1));
            p.setName(result.getString(2));
            p.setSurname(result.getString(3));
            p.setDoctorId(id);
            p.setPhoneNumber(result.getString(5));
            p.setAge(result.getInt(6));
            p.setDescription(result.getString(7));
            l.add(p);
        }
        return l;
        } catch (SQLException e){
            return null;
        }
    }

    public Patient getByID(Long id) {
        try {
            String query = "SELECT * FROM patient WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            Patient p = new Patient();
            while (result.next()){
                p = new Patient();
                p.setId(result.getLong(1));
                p.setName(result.getString(2));
                p.setSurname(result.getString(3));
                p.setDoctorId(id);
                p.setPhoneNumber(result.getString(5));
                p.setAge(result.getInt(6));
                p.setDescription(result.getString(7));
            }
            return p;
        } catch (SQLException e){
            return null;
        }
    }
}
