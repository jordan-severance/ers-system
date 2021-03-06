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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AllReimsForID")
public class AllReimsForID extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService service = new ReimbursementService();
        HttpSession session = req.getSession();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json;charset=UTF-8");
        int id = (Integer) session.getAttribute("seshUser");

//        int id = Integer.parseInt(req.getParameter("selectedID")); //they will choose an employee, and the request will contain the id for said emp
        List<ReimbursementRequest> all = null;
        all = service.getAllForId(id);
//        List<ReimbursementRequest> selected = new ArrayList();

        //see if they asked for pending or resolved
//        String pendingChoice = "";
//        pendingChoice = (req.getParameter("pendingChoice"));


//        //send one way or the other
//        if (pendingChoice.equals("pending")) {
//            //see pending requests
//            for (ReimbursementRequest tmp : all) {
//                if (tmp.isPending()) {        //if it is pending still
//                    selected.add(tmp);
//                }
//            }
//            String output = new Gson().toJson(selected);
//            out.print(output);
//            resp.sendRedirect("viewAllPending.html");
//        } else {
//            //see resolved requests
//            for (ReimbursementRequest tmp : all) {
//                if (!tmp.isPending()) {        //if it is resolved still
//                    selected.add(tmp);
//                }
//            }
            String output = new Gson().toJson(all);
            out.print(output);
            resp.sendRedirect("employee_requestsView.html");
        }
    }

