package ru.bogdanov.xrdent.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bogdanov.xrdent.dao.LaboratoryDAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Patient_DAO;
import ru.bogdanov.xrdent.entity.*;
import com.google.gson.Gson;
import ru.bogdanov.xrdent.entity.direction.RegisteredDirection;
import ru.bogdanov.xrdent.entity.direction.UnRegisteredDirection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {
    private LogDAO log_dao = new LogDAO();
    private Patient_DAO patient_dao = new Patient_DAO();
    private LaboratoryDAO lab_dao = new LaboratoryDAO();


    public boolean cheakToken(String token) {
        if (log_dao.cheakToken(token, "laboratory")) {
            return true;
        } else {
            return false;
        }
    }

    // создаем страницу для лабы
    @GetMapping("/{id}")
    public String getLab(HttpServletRequest req, @PathVariable Long id, Model model) {
        String token = (String) req.getAttribute("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Laboratory lab = lab_dao.getLabByID(id);
     //   List<RegistratedDirection> rl = lab_dao.getListOfRegistratedDirection(id);
     //   List<UnRegistratedDirection> unrl = lab_dao.getListOfUnRegistratedDirection();
     //   model.addAttribute("registrated_list", rl);
    //    model.addAttribute("ubregistrated_list", unrl);
        model.addAttribute("token", token);
        model.addAttribute("laboratory", lab);
        return "view/laboratory/laboratory";

    }



    @GetMapping("/{id}/regdir")
    public String regDirection(HttpServletRequest req,@PathVariable Long id, Model model) {
        String token = (String) req.getParameter("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Laboratory lab = lab_dao.getLabByID(id);
        List<UnRegisteredDirection> unrl = lab_dao.getListOfUnRegisteredDirection();
        model.addAttribute("unregistered_list", unrl);
        model.addAttribute("token", token);
        model.addAttribute("laboratory", lab);
        return "view/direction/direction";
    }
    @PostMapping("/{id}/regdir")
    public String postRegDirection(HttpServletRequest req,@PathVariable Long id, Model model) {
        String token = (String) req.getParameter("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Laboratory lab = lab_dao.getLabByID(id);
        List<UnRegisteredDirection> unrl = lab_dao.getListOfUnRegisteredDirection();
        model.addAttribute("unregistered_list", unrl);
        model.addAttribute("token", token);
        model.addAttribute("laboratory", lab);
        return "view/direction/direction";
    }

    @RequestMapping(value = "/getDirection", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDirection(@RequestParam(name = "id", required = true) Long id, HttpServletRequest req, HttpServletResponse res) {
        return new Gson().toJson(lab_dao.getUnRegDirByID(id));
    }
    @GetMapping("/{id}/direction")
    public String watchDirection(HttpServletRequest req,@PathVariable Long id, Model model) {
        String token = (String) req.getParameter("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Laboratory lab = lab_dao.getLabByID(id);
        List<RegisteredDirection> rl = lab_dao.getListOfRegisteredDirection(id);
        model.addAttribute("registered_list", rl);
        model.addAttribute("token", token);
        model.addAttribute("laboratory", lab);
        return "view/direction/registered_direction";
    }


}
