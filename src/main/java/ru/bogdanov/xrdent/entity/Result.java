package ru.bogdanov.xrdent.entity;


public class Result {

  private long id;
  private String dataSrc;
  private String description;
  private long directionId;
  private long doctorId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getDataSrc() {
    return dataSrc;
  }

  public void setDataSrc(String dataSrc) {
    this.dataSrc = dataSrc;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getDirectionId() {
    return directionId;
  }

  public void setDirectionId(long directionId) {
    this.directionId = directionId;
  }


  public long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(long doctorId) {
    this.doctorId = doctorId;
  }

}
