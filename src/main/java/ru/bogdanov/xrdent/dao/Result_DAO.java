package ru.bogdanov.xrdent.dao;

import ru.bogdanov.xrdent.entity.Storage;
import ru.bogdanov.xrdent.entity.result.Result;
import ru.bogdanov.xrdent.entity.result.Result_Cutted;
import ru.bogdanov.xrdent.entity.result.Result_Full;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
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
            statement.setLong(2,result.getDataSrc());
            statement.setString(3,result.getDescription());
            statement.setLong(4,result.getDirectionId());
            statement.execute();
        } catch (SQLException e){

        }
    }
    public List<Result_Cutted> getListOfResultsByDocId(Long doc_id){
        try {
            String query = "SELECT name, surname, phone_number,ID FROM result_for_doctor WHERE Doctor_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,doc_id);
            ResultSet result = statement.executeQuery();
            List<Result_Cutted> l = new ArrayList<>();
            while (result.next()){
                Result_Cutted d = new Result_Cutted();
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

    public Result_Full getResultById(Long id) {
        try {
            String query = "SELECT Name, Surname, Age, Phone_Number, Data_SRC, Result_Description, Doctor_Comment, Patient_Comment FROM result_for_doctor WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Result_Full d = new Result_Full();
                d.setName(result.getString(1));
                d.setSurname(result.getString(2));
                d.setAge(result.getInt(3));
                d.setPhone_number(result.getString(4));
                d.setData_src(result.getString(5));
                d.setResult_description(result.getString(6));
                d.setDoctor_comment(result.getString(7));
                d.setPatient_comment(result.getString(8));
                return d;
            }
          return null;
        } catch (SQLException e){
            return null;
        }
    }

    public Long save(Storage st) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO storage(Data) VALUE (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            Blob blob = new SerialBlob(st.getZip() );
            statement.setBlob(1,blob);
            statement.execute();

            String query2 = "SELECT LAST_INSERT_ID()";
            statement = connection.prepareStatement(query2);
            ResultSet result = statement.executeQuery();
            connection.commit();
            connection.setAutoCommit(true);
            while (result.next()){
                return result.getLong(1);
            }
            return null;
        } catch (SQLException e){
            return null;
        }
    }

    public Storage downloadzip(Long id) {
        try {
            String query = "SELECT * FROM storage WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Storage d = new Storage();
                d.setID(result.getLong(1));
                d.setBlob(result.getBlob(2));
                return d;
            }
            return null;
        } catch (SQLException e){
            return null;
        }
    }
}
