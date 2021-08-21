package ru.bogdanov.xrdent.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bogdanov.xrdent.dao.Direction_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Result_DAO;
import ru.bogdanov.xrdent.entity.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequestMapping("result")
public class ResultController {
    LogDAO log_dao = new LogDAO();
    Result_DAO result_dao = new Result_DAO();
    Direction_DAO direction_dao = new Direction_DAO();
    public boolean cheakToken(String token) {
        if (log_dao.cheakToken(token, "laboratory")) {
            return true;
        } else {
            return false;
        }
    }
    @PostMapping("result")
    public String postResult(HttpServletRequest req, HttpServletResponse res, Model model){
        String token = (String) req.getParameter("token");
        Long direction_id = Long.parseLong((String) req.getParameter("direction_id"));
        Long laboratory_id = Long.parseLong((String) req.getParameter("laboratory_id"));
        String src =  req.getParameter("src");
        String description =  req.getParameter("description");
        if (!cheakToken(token)) {
            return "view/login/login";
        }
        Result result = new Result(src,description,direction_id);
        direction_dao.close_direction(direction_id);
        result_dao.post_result(result);
        return "forward:/laboratory/" + laboratory_id;
    }
}
