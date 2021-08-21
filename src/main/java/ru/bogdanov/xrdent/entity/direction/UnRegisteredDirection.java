package ru.bogdanov.xrdent.entity.direction;

public class UnRegisteredDirection extends Direction{

    private String patient_name;
    private String patient_surname;
    private int patient_Age;
    private String phone_Number;

    public long getDirection_Id() {
        return id;
    }

    public void setDirection_Id(long direction_Id) {
        this.id = direction_Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }


}
