package ru.bogdanov.xrdent.entity;


public class Patient {

  private long id;
  private String name;
  private String surname;
  private long doctorId;
  private String phoneNumber;
  private int Age;
  private String description;


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
    return Age;
  }

  public void setAge(int age) {
    Age = age;
  }

  public void setDescription(String string) {
    this.description = string;
  }

  public String getDescription() {
    return description;
  }
}
