package com.ex.ers.newServletsAndData;

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

@WebServlet("/ApproveReim")
public class ApproveReim extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService service = new ReimbursementService();
        ServletOutputStream out = resp.getOutputStream();

        //we need to give these three to the service layer
        ReimbursementRequest reimbursementRequest;
        boolean approve;
        String managerName;

        int reimID=Integer.parseInt(req.getParameter("reimID"));
        reimbursementRequest = service.findById(reimID);
        managerName = req.getParameter("managerName");
        approve = Boolean.parseBoolean(req.getParameter("approveChoice"));

        service.markApproved(reimbursementRequest,approve,managerName);


    }
}

