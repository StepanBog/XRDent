package ru.bogdanov.xrdent.entity.direction;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Direction{

    long id;
    long doctor_id;
    long patient_id;
    String description;
    java.sql.Timestamp post_dataTime;
    private String phone_number;
    boolean is_close = false;

    public Direction(String doctorId, String patientId, String description) {
        this.doctor_id = Long.parseLong(doctorId);
        this.patient_id = Long.parseLong(patientId);
        this.description = description;
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        this.post_dataTime = Timestamp.valueOf(sdf.format(dt));
    }

    public Direction() {

    }

    public Timestamp getPost_dataTime() {
        return post_dataTime;
    }

    public void setPost_dataTime(Timestamp post_dataTime) {
        this.post_dataTime = post_dataTime;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDataTime() {
        return post_dataTime;
    }

    public void setDataTime(Timestamp dataTime) {
        this.post_dataTime = dataTime;
    }

    public boolean isIs_close() {
        return is_close;
    }

    public void setIs_close(boolean is_close) {
        this.is_close = is_close;
    }
}
