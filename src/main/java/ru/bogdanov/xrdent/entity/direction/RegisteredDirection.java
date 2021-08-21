package ru.bogdanov.xrdent.entity.direction;


import java.sql.Timestamp;

public class RegisteredDirection extends Direction {

  private long laboratoryId;
  private java.sql.Timestamp registrationDateTime;

  private String patient_name;
  private String patient_surname;
  private int patient_Age;

  private String doctor_name;
  private String doctor_surname;

  public RegisteredDirection() {
  }

  public long getDirectionId() {
    return this.id;
  }

  public void setDirectionId(long directionId) {
    this.id = directionId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public long getPatient_id() {
    return patient_id;
  }

  public void setPatient_id(long patient_id) {
    this.patient_id = patient_id;
  }

  public String getPatient_name() {
    return patient_name;
  }

  public void setPatient_name(String patient_name) {
    this.patient_name = patient_name;
  }

  public String getPatient_surname() {
    return patient_surname;
  }

  public void setPatient_surname(String patient_surname) {
    this.patient_surname = patient_surname;
  }

  public int getPatient_Age() {
    return patient_Age;
  }

  public void setPatient_Age(int patient_Age) {
    this.patient_Age = patient_Age;
  }

  public long getDoctor_id() {
    return doctor_id;
  }

  public void setDoctor_id(long doctor_id) {
    this.doctor_id = doctor_id;
  }

  public String getDoctor_name() {
    return doctor_name;
  }

  public void setDoctor_name(String doctor_name) {
    this.doctor_name = doctor_name;
  }

  public String getDoctor_surname() {
    return doctor_surname;
  }

  public void setDoctor_surname(String doctor_surname) {
    this.doctor_surname = doctor_surname;
  }
}
