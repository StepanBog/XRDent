package ru.bogdanov.xrdent.dao;

import org.springframework.stereotype.Component;
import ru.bogdanov.xrdent.entity.direction.Direction;
import ru.bogdanov.xrdent.entity.direction.Direction_Cutted;
import ru.bogdanov.xrdent.entity.direction.RegisteredDirection;
import ru.bogdanov.xrdent.entity.direction.UnRegisteredDirection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Direction_DAO {
    private Connection connection;
    private DB_Properties properties;
    public Direction_DAO() {
        properties = new DB_Properties();
        try {
            connection = DriverManager.getConnection(properties.toString(),properties.getUsername(),properties.getPassword());
        }  catch (SQLException e){

        }
    }

    public UnRegisteredDirection getUnRegDirByID(Long id) {
        try {
            String query = "SELECT * FROM unregistered_direction WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                UnRegisteredDirection d = new UnRegisteredDirection();
                d.setPatient_name(result.getString(1));
                d.setPatient_surname(result.getString(2));
                d.setPhone_number(result.getString(3));
                d.setPatient_Age(result.getInt(4));
                d.setDescription(result.getString(5));
                d.setDirection_Id(result.getLong(7));
                return d;
            }
            return null;
        } catch (SQLException e){
            return null;
        }
    }
    public RegisteredDirection getRegDirByID(Long id) {
        try {
            String query = "SELECT * FROM registered_direction WHERE direction_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                RegisteredDirection d = new RegisteredDirection();
                d.setPatient_id(result.getLong(1));
                d.setPatient_name(result.getString(2));
                d.setPatient_surname(result.getString(3));
                d.setPatient_Age(result.getInt(4));
                d.setPhone_number(result.getString(5));
                d.setDirectionId(result.getLong(6));
                d.setDescription(result.getString(7));
                d.setRegistrationDateTime(result.getTimestamp(8));
                d.setLaboratoryId(result.getLong(9));
                d.setDoctor_id(result.getLong(10));
                d.setDoctor_name(result.getString(11));
                d.setDoctor_surname(result.getString(12));
                return d;
            }
            return null;
        } catch (SQLException e){
            return null;
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
    public List<Direction_Cutted> getListOfUnRegisteredDirection(){
        try {
            String query = "SELECT Name, Surname, Phone_Number, ID FROM unregistered_direction";
            PreparedStatement statement = connection.prepareStatement(query);
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

    public void postDirection(Direction d) {
        try {
            String query = "INSERT INTO direction(doctor_id, patient_id, description, post_datatime) VALUE(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,d.getDoctor_id());
            statement.setLong(2,d.getPatient_id());
            statement.setString(3,d.getDescription());
            statement.setTimestamp(4,d.getDataTime());
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public void regDir(Long direction_id, Long laboratory_id, LocalDateTime date) {
        try {
            String query = "UPDATE direction SET Laboratory_ID = ?,Registration_DataTime = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,laboratory_id);
            statement.setTimestamp(2,Timestamp.valueOf(date));
            statement.setLong(3,direction_id);
            statement.execute();
        } catch (SQLException e) {

        }
    }
}
