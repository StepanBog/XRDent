package ru.bogdanov.xrdent.entity;


import java.sql.Timestamp;

public class Direction {

  private long id;
  private long doctorId;
  private long patientId;
  private String description;
  private java.sql.Timestamp dataTime;
  private long laboratoryId;
  private java.sql.Timestamp registrationDateTime;

  public Direction(String doctorId, String patientId, String description) {
    this.doctorId = Long.parseLong(doctorId);
    this.patientId = Long.parseLong(patientId);
    this.description = description;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    java.util.Date dt = new java.util.Date();
    this.dataTime = Timestamp.valueOf(sdf.format(dt));
  }

  public Direction() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(long doctorId) {
    this.doctorId = doctorId;
  }


  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Timestamp getDataTime() {
    return dataTime;
  }

  public void setDataTime(java.sql.Timestamp dataTime) {
    this.dataTime = dataTime;
  }

  public long getLaboratoryId() {
    return laboratoryId;
  }

  public void setLaboratoryId(long laboratoryId) {
    this.laboratoryId = laboratoryId;
  }

  public Timestamp getRegistrationDateTime() {
    return registrationDateTime;
  }

  public void setRegistrationDateTime(Timestamp registrationDateTime) {
    this.registrationDateTime = registrationDateTime;
  }
}
