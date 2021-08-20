package ru.bogdanov.xrdent.entity;


public class RegistratedDirection {

  private long id;
  private long directionId;
  private long laboratoryId;
  private java.sql.Timestamp registrationDateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDirectionId() {
    return directionId;
  }

  public void setDirectionId(long directionId) {
    this.directionId = directionId;
  }


  public long getLaboratoryId() {
    return laboratoryId;
  }

  public void setLaboratoryId(long laboratoryId) {
    this.laboratoryId = laboratoryId;
  }


  public java.sql.Timestamp getRegistrationDateTime() {
    return registrationDateTime;
  }

  public void setRegistrationDateTime(java.sql.Timestamp registrationDateTime) {
    this.registrationDateTime = registrationDateTime;
  }

}
