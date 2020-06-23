package com.ex.ers.newServletsAndData;

import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.PersonService;
import com.ex.ers.services.ReimbursementService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@WebServlet("/UpdateMyInformation")
public class UpdateMyInformation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        let session = {"id" : null, "username" : "null", "pw" : "null",
//        "fname" : "null1", "lname": "null2",
//         "address" : "null", "jobTitle" : "null", "isManager" : "null",
//         "currentPage" : "index.html", "error" : "false"};

        //xmlHttpRequest.send("fname="+afname+"&lname="+alname+"&address="+aaddress+"&jobtitle="+ajobtitle+"&username="+ausername+"&password="+apassword);

        //String id = req.getParameter("id");
        String username = req.getParameter("username");
        String pw = req.getParameter("password");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String address = req.getParameter("address");
        String jobtitle = req.getParameter("jobtitle");
        //String isManager = req.getParameter("isManager");
        //String currentPage = req.getParameter("currentPage");
        //String error = req.getParameter("error");

        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");
        ConnectionUtils connectionUtils = new PostgresqlConnectionUtil();
        Connection con;

        String sql = "insert into " + "public.reimreqs " + "values " +
                "("+fname+", "+lname+", "+address+", "+jobtitle+", "+username+", "+pw+", "+null+")";
        try {
                Statement statement = connectionUtils.getConnection().createStatement();
                statement.execute(sql);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
