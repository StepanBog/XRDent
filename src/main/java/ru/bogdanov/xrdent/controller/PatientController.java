package ru.bogdanov.xrdent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdanov.xrdent.dao.Doctor_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Patient_DAO;
import ru.bogdanov.xrdent.entity.Doctor;
import ru.bogdanov.xrdent.entity.Patient;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
@RequestMapping("/patient")
public class PatientController {
    private LogDAO log_dao = new LogDAO();
    private Doctor_DAO doc_dao = new Doctor_DAO();
    private Patient_DAO patient_dao = new Patient_DAO();


    public boolean cheakToken(String token){
        if (log_dao.cheakToken(token,"doctor")){
            return true;
        }else{
            return false;
        }
    }

    // загружаем данные пациента
    @GetMapping("/{id}")
    public String getPatient(HttpServletRequest req,  @PathVariable Long id, Model model) {
        String token = req.getParameter("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Patient p = patient_dao.getByID(id);
        model.addAttribute("token", token);
        model.addAttribute("patient", p);
        model.addAttribute("doctor_id", req.getParameter("doctor"));
        return "view/patient/patient";

    }


}
