package ru.bogdanov.xrdent.controller;

import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bogdanov.xrdent.dao.Direction_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Result_DAO;
import ru.bogdanov.xrdent.entity.Storage;
import ru.bogdanov.xrdent.entity.result.Result;
import ru.bogdanov.xrdent.entity.result.Result_Cutted;
import ru.bogdanov.xrdent.entity.result.Result_Full;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
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
    public String postResult(@RequestParam("file") MultipartFile file,HttpServletRequest req, HttpServletResponse res, Model model){

        String token = (String) req.getParameter("token");
        Long direction_id = Long.parseLong((String) req.getParameter("direction_id"));
        Long laboratory_id = Long.parseLong((String) req.getParameter("laboratory_id"));
        String description =  req.getParameter("description");
        if (!cheakToken(token,"laboratory")) {
            return "view/login/login";
        }
        Long src = uploadToDB(file);
        Result result = new Result(src,description,direction_id);
        direction_dao.close_direction(direction_id);
        result_dao.post_result(result);
        model.addAttribute("token",token);
        return "forward:/laboratory/" + laboratory_id;
    }
    @RequestMapping(value = "/download", produces="application/zip")
    @ResponseBody
    public void downloadFromDB(HttpServletRequest req, HttpServletResponse response) throws IOException, SQLException {
        Long st_id = Long.parseLong((String) req.getParameter("st_id"));
        Storage st = result_dao.downloadzip(st_id);
        String token = (String) req.getParameter("token");
        if (!cheakToken(token,"doctor")) {
            return;
        }
        Blob blob = st.getBlob();
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");
        response.setContentLength((int) blob.length());

        InputStream inputStream = blob.getBinaryStream();
        OutputStream outputStream = response.getOutputStream();
        int bytes;
        while ((bytes = inputStream.read()) != -1) {
            outputStream.write(bytes);
        }

        inputStream.close();
        outputStream.close();


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

  public Long uploadToDB(MultipartFile file) {
      Storage st = new Storage();
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      try {
          st.setZip(file.getBytes());
      } catch (IOException e) {
          e.printStackTrace();
      }
      Long src = result_dao.save(st);
      return src;
  }
}
