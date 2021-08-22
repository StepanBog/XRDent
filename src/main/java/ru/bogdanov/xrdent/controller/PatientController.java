package ru.bogdanov.xrdent.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bogdanov.xrdent.dao.Doctor_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Patient_DAO;
import ru.bogdanov.xrdent.entity.patient.Patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private LogDAO log_dao = new LogDAO();
    private Doctor_DAO doc_dao = new Doctor_DAO();
    private Patient_DAO patient_dao = new Patient_DAO();


    public boolean cheakToken(String token) {
        if (log_dao.cheakToken(token, "doctor")) {
            return true;
        } else {
            return false;
        }
    }

    // загружаем данные пациента
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPatient(HttpServletRequest req, @RequestParam(name = "id", required = true) Long id, Model model, @RequestParam(name = "token", required = true) String token) {
        if (!cheakToken(token)) {
            return null;
        }
        Patient p = patient_dao.getByID(id);
        return new Gson().toJson(p);


    }

    @GetMapping("/add")
    public String addPatient(HttpServletRequest req, HttpServletResponse res, Model model) {
        String token = req.getParameter("token");
        Long id = Long.parseLong(req.getParameter("doctor_id"));
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        model.addAttribute("doctor", id);
        model.addAttribute("token", token);
        return "view/patient/add";
    }

    @PostMapping("/add")
    public String postPatient(HttpServletRequest req, HttpServletResponse res, Model model) {
        String token = req.getParameter("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Patient p = new Patient(req.getParameter("name"),
                                req.getParameter("surname"),
                                req.getParameter("age"),
                                req.getParameter("description"),
                                req.getParameter("phone_number"),
                                req.getParameter("doctor_id"));
        patient_dao.addPatient(p);
        model.addAttribute("doctor", req.getParameter("doctor_id"));
        model.addAttribute("token", token);
        return "forward:/doc/" + req.getParameter("doctor_id");

    }


}
