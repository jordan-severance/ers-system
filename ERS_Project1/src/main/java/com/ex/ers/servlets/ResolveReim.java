package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.ReimbursementService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ResolveReim")
public class ResolveReim extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService service = new ReimbursementService();
        ServletOutputStream out = resp.getOutputStream();

        //we need to give these three to the service layer
        ReimbursementRequest reimbursementRequest;
        boolean approve;
        String managerName;

//        String jsonString = req.getParameter("selectedReim");
//        Object reim = new Gson().fromJson(jsonString,Object.class);
//        reimbursementRequest = (ReimbursementRequest) reim;
//        approve = Boolean.parseBoolean(req.getParameter("appoveChoice"));
//        Object manager = req.getAttribute("seshUser");
//        String fname = ((Person) manager).getFname();
//        String lname = ((Person) manager).getLname();
//        managerName = fname+lname;
        int id = Integer.parseInt(req.getParameter("reimID"));
        reimbursementRequest = service.findById(id);
        approve = Boolean.parseBoolean(req.getParameter("approveChoice"));
        managerName = req.getParameter("managerName");

        service.markApproved(reimbursementRequest,approve,managerName);
        out.print(new Gson().toJson(reimbursementRequest));


    }
}
