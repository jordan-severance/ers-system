package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.PersonService;
import com.ex.ers.services.ReimbursementService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetAllEmployees")
public class GetAllEmployees extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();
        HttpSession session = req.getSession();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");
        List<Person> all;
        List<Person> emps = new ArrayList();

        all = service.getAllEmployees();
        for(Person tmp : all){
            if(!tmp.isManager()){
                emps.add(tmp);
            }
        }
        String output = new Gson().toJson(emps);
        out.print(output);
        resp.sendRedirect("manager_homepage.html");
    }
}
