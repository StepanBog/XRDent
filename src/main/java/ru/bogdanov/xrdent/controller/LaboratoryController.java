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
    private LaboratoryDAO lab_dao = new LaboratoryDAO();


    public boolean cheakToken(String token) {
        if (log_dao.cheakToken(token, "laboratory")) {
            return true;
        } else {
            return false;
        }
    }
    @PostMapping("/{id}")
    public String redirGetDoc(HttpServletRequest req,  @PathVariable Long id, Model model){
        return getLab(req,id,model);
    }
    // создаем страницу для лабы
    @GetMapping("/{id}")
    public String getLab(HttpServletRequest req, @PathVariable Long id, Model model) {
        String token = (String) req.getAttribute("token");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Laboratory lab = lab_dao.getLabByID(id);
        model.addAttribute("token", token);
        model.addAttribute("laboratory", lab);
        return "view/laboratory/laboratory";

    }



}
