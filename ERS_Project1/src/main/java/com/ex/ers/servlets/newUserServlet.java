package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

@WebServlet("/newUserServlet")
public class newUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService service = new PersonService();
        HttpSession session = req.getSession();
        ServletOutputStream out = resp.getOutputStream();

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String address = req.getParameter("address");
        String jobtitle = req.getParameter("jobtitle");
        String username = req.getParameter("username");
        String pw = req.getParameter("pw");

        // make and send json object
        Person person = null;
        person = service.saveNewUser(fname, lname, address, jobtitle, username, pw);
        Person check = service.findByName(username);
        resp.setContentType("application/json;charset=UTF-8");
        String personJsonString = new Gson().toJson(check);

        session.setAttribute("seshUser",check.getId());
        out.print(personJsonString);
        resp.sendRedirect("employee_homepage.html");

    }
}
