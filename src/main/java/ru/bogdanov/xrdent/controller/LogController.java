package ru.bogdanov.xrdent.controller;
import javafx.util.Pair;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import ru.bogdanov.xrdent.dao.Doctor_DAO;
import ru.bogdanov.xrdent.dao.LogDAO;
import ru.bogdanov.xrdent.dao.Patient_DAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping("/log")
public class LogController {
    private LogDAO log_dao = new LogDAO();
    private Doctor_DAO doc_dao = new Doctor_DAO();
    private Patient_DAO pat_dao = new Patient_DAO();
    @GetMapping("/login")
    public String login(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        return "view/login/login";
      //  return "denied";
    }
   @GetMapping("/auth")
    public String auth(HttpServletRequest req, Model model) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (role.equals("Doctor")) {
            return docAuth(login,password,model);
        }
        else {
            return labAuth(login,password,model);
        }

    }
    private String docAuth(String login, String password, Model model){
        Pair<String, Long> pair = log_dao.docLogIN(login, password);
        String token = pair.getKey();
        Long id = pair.getValue();
        model.addAttribute("token",token);
        if (token.equals(""))
            return "view/login/denied";
        else {
            return "forward:/doc/" + id.toString();
        }
    }
    private String labAuth(String login, String password, Model model){
        Pair<String, Long> pair = log_dao.labLogIN(login, password);
        String token = pair.getKey();
        Long id = pair.getValue();
        model.addAttribute("token",token);
        if (token.equals(""))
            return "view/login/denied";
        else {
          //  Long id = doc_dao.getIDByLog(login);
            return "forward:/laboratory/" + id.toString();
        }
    }
    // тестовый вариант загрузки из сервера zip
  /*  @GetMapping("/downloadZip")
    public void downloadFile(HttpServletResponse response) {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        String fileName = "C:/Users/stepa/OneDrive/Рабочий стол/file.txt";

        System.out.println("############# file size ###########" + 1);

        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            FileSystemResource resource = new FileSystemResource(fileName);

            ZipEntry e = new ZipEntry(resource.getFilename());
            // Configure the zip entry, the properties of the file
            e.setSize(resource.contentLength());
            e.setTime(System.currentTimeMillis());
            // etc.
            zippedOut.putNextEntry(e);
            // And the content of the resource:
            StreamUtils.copy(resource.getInputStream(), zippedOut);
            zippedOut.closeEntry();
            zippedOut.finish();
        } catch (Exception e) {
            // Exception handling goes here
        }
    }*/
}
