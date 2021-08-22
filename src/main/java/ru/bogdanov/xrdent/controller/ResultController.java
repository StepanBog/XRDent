package ru.bogdanov.xrdent.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bogdanov.xrdent.dao.Direction_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Result_DAO;
import ru.bogdanov.xrdent.entity.patient.Patient;
import ru.bogdanov.xrdent.entity.result.Result;
import ru.bogdanov.xrdent.entity.result.Result_Cutted;
import ru.bogdanov.xrdent.entity.result.Result_Full;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequestMapping("/result")
public class ResultController {
    LogDAO log_dao = new LogDAO();
    Result_DAO result_dao = new Result_DAO();
    Direction_DAO direction_dao = new Direction_DAO();
    public boolean cheakToken(String token,String role) {
        if (log_dao.cheakToken(token, role)) {
            return true;
        } else {
            return false;
        }
    }
    @PostMapping("/result")
    public String postResult(HttpServletRequest req, HttpServletResponse res, Model model){
        String token = (String) req.getParameter("token");
        Long direction_id = Long.parseLong((String) req.getParameter("direction_id"));
        Long laboratory_id = Long.parseLong((String) req.getParameter("laboratory_id"));
        String src =  req.getParameter("src");
        String description =  req.getParameter("description");
        if (!cheakToken(token,"laboratory")) {
            return "view/login/login";
        }
        Result result = new Result(src,description,direction_id);
        direction_dao.close_direction(direction_id);
        result_dao.post_result(result);
        model.addAttribute("token",token);
        return "forward:/laboratory/" + laboratory_id;
    }

    @GetMapping("/getresults")
    public String getListOfRes(HttpServletRequest req, HttpServletResponse res, Model model){
        String token = (String) req.getParameter("token");
        Long doc_id = Long.parseLong((String) req.getParameter("doctor_id"));
        if (!cheakToken(token,"doctor")) {
            return "view/login/login";
        }
        List<Result_Cutted> l = result_dao.getListOfResultsByDocId(doc_id);
        model.addAttribute("token",token);
        model.addAttribute("result_list",l);
        return "view/result/result";
    }

    @RequestMapping(value = "/getresult", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getResult(HttpServletRequest req, @RequestParam(name = "id", required = true) Long id, Model model, @RequestParam(name = "token", required = true) String token) {
        if (!cheakToken(token,"doctor")) {
            return "view/login/login";
        }
        Result_Full r = result_dao.getResultById(id);
        return new Gson().toJson(r);


    }
}
