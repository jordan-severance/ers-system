package com.ex.ers.newServletsAndData;

import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetAllEmployeesNEW")
public class GetAllEmployees extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> all = new PersonService().getAllEmployees();

        List<Person> employees = new ArrayList();
        for(Person tmp : all){
            if(!tmp.isManager()){
                employees.add(tmp);
            }
        }

        resp.setContentType("application/json;charset=UTF-8");
        resp.getOutputStream().print(new Gson().toJson(employees));
    }
}
