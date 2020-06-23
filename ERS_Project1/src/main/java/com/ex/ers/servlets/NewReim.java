package com.ex.ers.servlets;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.PersonService;
import com.ex.ers.services.ReimbursementService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;

@WebServlet("/NewReim")
public class NewReim extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService service = new ReimbursementService();
        resp.setContentType("application/json;charset=UTF-8");

        Float amount = Float.valueOf(req.getParameter("amount"));
        String comment = req.getParameter("comment");
        String requester = req.getParameter("requester");
        int reqID = Integer.parseInt(req.getParameter("reqID"));

        ReimbursementRequest reim = new ReimbursementRequest();
        reim = service.saveNewReimReq(requester, amount, comment, reqID);
        resp.getOutputStream().print(new Gson().toJson(reim));

    }
}
