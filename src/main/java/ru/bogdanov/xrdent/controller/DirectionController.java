package ru.bogdanov.xrdent.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bogdanov.xrdent.dao.Direction_DAO;
import ru.bogdanov.xrdent.dao.LaboratoryDAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Patient_DAO;
import ru.bogdanov.xrdent.entity.Laboratory;
import ru.bogdanov.xrdent.entity.direction.Direction_Cutted;
import ru.bogdanov.xrdent.entity.direction.RegisteredDirection;
import ru.bogdanov.xrdent.entity.direction.UnRegisteredDirection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/direction")
public class DirectionController {
    private LogDAO log_dao = new LogDAO();
    private Direction_DAO direction_dao = new Direction_DAO();

    public boolean cheakToken(String token) {
        if (log_dao.cheakToken(token, "laboratory")) {
            return true;
        } else {
            return false;
        }
    }
    @GetMapping("/regdir")
    public String regDirection(HttpServletRequest req, Model model) {
        String token = (String) req.getParameter("token");
        Long laboratory_id = Long.parseLong( req.getParameter("laboratory_id"));
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        List<Direction_Cutted> unrl = direction_dao.getListOfUnRegisteredDirection();
        model.addAttribute("unregistered_list", unrl);
        model.addAttribute("laboratory_id", laboratory_id);
        model.addAttribute("token", token);
        return "view/direction/direction";
    }
    @PostMapping("/regdir")
    public String postRegDirection(HttpServletRequest req, Model model) throws ParseException {
        String token = (String) req.getParameter("token");
        Long laboratory_id = Long.parseLong( req.getParameter("laboratory_id"));
        Long direction_id = Long.parseLong( req.getParameter("direction_id"));
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        LocalDateTime date_time = LocalDateTime.parse(req.getParameter("date_time"));
        direction_dao.regDir(direction_id,laboratory_id,date_time);
        model.addAttribute("token", token);
        return "forward:/laboratory/" + laboratory_id;
    }

    @GetMapping("/seedir")
    public String watchDirection(HttpServletRequest req, Model model) {
        String token = (String) req.getParameter("token");
        Long laboratory_id = Long.parseLong( req.getParameter("laboratory_id"));
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        List<Direction_Cutted> rl = direction_dao.getListOfRegisteredDirection(laboratory_id);
        model.addAttribute("registered_list", rl);
        model.addAttribute("laboratory_id", laboratory_id);
        model.addAttribute("token", token);
        return "view/direction/registered_direction";
    }

    @RequestMapping(value = "/getDirection", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDirection(@RequestParam(name = "id", required = true) Long id, HttpServletRequest req, HttpServletResponse res, @RequestParam(name = "token", required = true) String token) {
        if (!cheakToken(token)) {
            return null;
        }
        return new Gson().toJson(direction_dao.getUnRegDirByID(id));
    }
    @RequestMapping(value = "/getRegDirection", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRegDirection(@RequestParam(name = "id", required = true) Long id, HttpServletRequest req, HttpServletResponse res, @RequestParam(name = "token", required = true) String token) {
        if (!cheakToken(token)) {
            return null;
        }
        return new Gson().toJson(direction_dao.getRegDirByID(id));
    }
}
