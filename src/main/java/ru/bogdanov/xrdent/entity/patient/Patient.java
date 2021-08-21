package ru.bogdanov.xrdent.entity.patient;


public class Patient extends Patient_Cutted {
  private long doctorId;
  private int age;
  private String description;

  public Patient(String name, String surname, String age, String description, String phoneNumber, String doctor_id) {
    this.name = name;
    this.surname = surname;
    this.description = description;
    this.phoneNumber = phoneNumber;
    this.doctorId = Long.parseLong(doctor_id);
    this.age = Integer.parseInt(age);

  }
  public Patient(){

  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }


  public long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(long doctorId) {
    this.doctorId = doctorId;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setDescription(String string) {
    this.description = string;
  }

  public String getDescription() {
    return description;
  }
}
