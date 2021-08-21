package ru.bogdanov.xrdent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdanov.xrdent.dao.Doctor_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Patient_DAO;
import ru.bogdanov.xrdent.entity.direction.Direction;
import ru.bogdanov.xrdent.entity.Doctor;
import ru.bogdanov.xrdent.entity.patient.Patient;
import ru.bogdanov.xrdent.entity.patient.Patient_Cutted;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
@RequestMapping("/doc")
public class DoctorController {
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

    // создаем страницу для дока
    @GetMapping("/{id}")
    public String getDoc(HttpServletRequest req,  @PathVariable Long id, Model model) {
        String token = (String) req.getAttribute("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Doctor d = doc_dao.getByID(id);
        model.addAttribute("token", token);
        model.addAttribute("doctor", d);
        return "view/doctor/doctor";

    }
    @GetMapping("/{id}/patients")
    public String getPatients(HttpServletRequest req,  @PathVariable Long id, Model model) {
        String token = req.getParameter("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Doctor d = doc_dao.getByID(id);
        List<Patient_Cutted> l = patient_dao.getListByDocID(d.getId());
        model.addAttribute("patient_list", l);
        model.addAttribute("token", token);
        model.addAttribute("doctor", d);
        return "view/patient/patient";

    }
    @PostMapping("/{id}")
    public String redirGetDoc(HttpServletRequest req,  @PathVariable Long id, Model model){
        return getDoc(req,id,model);
    }

    // создаем страницу для направы
    @GetMapping("/direction")
    public String createDirection(HttpServletRequest req, Model model){
        String token = (String) req.getParameter("token");
        Long doctor_id = Long.parseLong((String) req.getParameter("doctor"));
        Long patient_id = Long.parseLong((String) req.getParameter("patient"));
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Doctor d = doc_dao.getByID(doctor_id);
        Patient p = patient_dao.getByID(patient_id);
        model.addAttribute("token", token);
        model.addAttribute("patient", p);
        model.addAttribute("doctor", d);
        return "view/direction/create_direction";
    }
    // сохраняем направление в бд
    @PostMapping("/direction")
    public String postDirection(HttpServletRequest req, Model model){
        String token = (String) req.getParameter("token");
        String doctor_id =  (String) req.getParameter("doctor_id");
        String patient_id = (String) req.getParameter("patient_id");
        String descrition = (String) req.getParameter("description");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Direction d = new Direction(doctor_id,patient_id,descrition);
        doc_dao.postDirection(d);
        model.addAttribute("token", token);
        return "forward:/doc/" + doctor_id;
    }


}
