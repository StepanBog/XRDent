package ru.bogdanov.xrdent.dao;

import ru.bogdanov.xrdent.entity.*;
import ru.bogdanov.xrdent.entity.direction.RegisteredDirection;
import ru.bogdanov.xrdent.entity.direction.UnRegisteredDirection;

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
    public List<RegisteredDirection> getListOfRegisteredDirection(Long lab_id){
        try {
            String query = "SELECT * FROM registered_direction WHERE laboratory_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,lab_id);
            ResultSet result = statement.executeQuery();
            List<RegisteredDirection> l = new ArrayList<>();
            while (result.next()){
                RegisteredDirection d = new RegisteredDirection();
                d.setPatient_id(result.getLong(1));
                d.setPatient_name(result.getString(2));
                d.setPatient_surname(result.getString(3));
                d.setPatient_Age(result.getInt(4));
                d.setDirectionId(result.getLong(5));
                d.setDescription(result.getString(6));
                d.setRegistrationDateTime(result.getTimestamp(7));
                d.setLaboratoryId(result.getLong(8));
                d.setDoctor_id(result.getLong(9));
                d.setDoctor_name(result.getString(10));
                d.setDoctor_surname(result.getString(11));
                l.add(d);
            }
            return l;
        } catch (SQLException e){
            return null;
        }
    }
    public List<UnRegisteredDirection> getListOfUnRegisteredDirection(){
        try {
            String query = "SELECT * FROM unregistered_direction";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            List<UnRegisteredDirection> l = new ArrayList<>();
            while (result.next()){
                UnRegisteredDirection d = new UnRegisteredDirection();
                d.setPatient_name(result.getString(1));
                d.setPatient_surname(result.getString(2));
                d.setPhone_Number(result.getString(3));
                d.setPatient_Age(result.getInt(4));
                d.setDescription(result.getString(5));
                d.setDirection_Id(result.getLong(7));
                l.add(d);
            }
            return l;
        } catch (SQLException e){
            return null;
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
                d.setPhone_Number(result.getString(3));
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
}
