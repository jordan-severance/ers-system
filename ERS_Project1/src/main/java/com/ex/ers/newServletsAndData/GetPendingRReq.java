package com.ex.ers.newServletsAndData;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetPendingRReq")
public class GetPendingRReq extends HttpServlet {
    String SCHEMA_TABLE = "public.reimreqs";
    private ConnectionUtils connectionUtils = new PostgresqlConnectionUtil();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String sql = "select * from " + SCHEMA_TABLE + " where pending = true";
        Connection con;
        Statement statement;
        ResultSet rs = null;
        try {
            con = connectionUtils.getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
        } catch (Exception exception) {
            System.out.println(exception);
        }

        List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
        try {
            while (rs.next()) {
                ReimbursementRequest reimbursementRequest = new ReimbursementRequest();
                reimbursementRequest.setPending(rs.getBoolean("pending"));
                reimbursementRequest.setApproved(rs.getBoolean("approved"));
                reimbursementRequest.setRequester(rs.getString("requester"));
                reimbursementRequest.setApprover(rs.getString("approver"));
                reimbursementRequest.setAmount(rs.getFloat("amount"));
                reimbursementRequest.setComment(rs.getString("scomment"));
                reimbursementRequest.setId(rs.getInt("id"));
                reimbursementRequest.setRequestorid(rs.getInt("requestorid"));
                reimbursementRequests.add(reimbursementRequest);

            }
        } catch (Exception e){
            System.out.println(e);
        }
        resp.getOutputStream().print(new Gson().toJson(reimbursementRequests));
    }
}