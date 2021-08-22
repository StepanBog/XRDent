package ru.bogdanov.xrdent.entity.result;

public class Result_Full extends Result_Cutted{
    Integer age;
    String data_src;
    String result_description;
    String doctor_comment;
    String patient_comment;

    public Result_Full() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getData_src() {
        return data_src;
    }

    public void setData_src(String data_src) {
        this.data_src = data_src;
    }

    public String getResult_description() {
        return result_description;
    }

    public void setResult_description(String result_description) {
        this.result_description = result_description;
    }

    public String getDoctor_comment() {
        return doctor_comment;
    }

    public void setDoctor_comment(String doctor_comment) {
        this.doctor_comment = doctor_comment;
    }

    public String getPatient_comment() {
        return patient_comment;
    }

    public void setPatient_comment(String patient_comment) {
        this.patient_comment = patient_comment;
    }
}
