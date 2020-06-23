package com.ex.ers.DAO;

import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.ReimbursementService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements DAOs<ReimbursementRequest> {
    private ConnectionUtils connectionUtils;

    public ReimbursementDAO() {
        connectionUtils = new PostgresqlConnectionUtil();
    }

    @Override
    public List<ReimbursementRequest> findAllByID(int id) { //id will be employee id number
        Connection conn = null;
        List<ReimbursementRequest> all = new ArrayList();
        Person requester = null;
        try {
            conn = connectionUtils.getConnection();
            //get the employee by their id number
            String sql0 = "Select  * from public.persons where emp_id =?";
            PreparedStatement ps0 = conn.prepareStatement(sql0);
            ps0.setInt(1,id);
            ResultSet rs0 = ps0.executeQuery();
            if (rs0.next()) {
                requester.setUsername(rs0.getString("username"));
                requester.setPw(rs0.getString("pass"));
                requester.setFname(rs0.getString("fname"));
                requester.setLname(rs0.getString("lname"));
                requester.setAddress(rs0.getString("address"));
                requester.setJobTitle(rs0.getString("jobtitle"));
                requester.setUsername(rs0.getString("username"));
                requester.setPw(rs0.getString("pw"));
                requester.setId(rs0.getInt("emp_id"));
                requester.setManager(rs0.getBoolean("manager"));
            }
            //make the requester a JsonObject
            String requesterString = new Gson().toJson(requester);
            JsonObject obj = new Gson().fromJson(requesterString,JsonObject.class);

            //give the JsonObject to the other db
            String sql = "Select * from public.reimreqs where requester =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1,obj);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ReimbursementRequest tmp = new ReimbursementRequest();
                tmp.setRequester(rs.getString("requester"));
                tmp.setComment(rs.getString("scomment"));
                tmp.setAmount(rs.getFloat("amount"));
                tmp.setApproved(rs.getBoolean("approved"));
                tmp.setPending(rs.getBoolean("pending"));
                tmp.setApprover(rs.getString("approver"));
                tmp.setId(rs.getInt("id"));
                all.add(tmp);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return all;
        }

    }

    @Override
    public List<ReimbursementRequest> findAll() {
        Connection conn = null;
        List<ReimbursementRequest> all = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String sql = "Select * from public.reimreqs";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ReimbursementRequest tmp = new ReimbursementRequest();
                tmp.setRequester(rs.getString("requester"));
                tmp.setComment(rs.getString("scomment"));
                tmp.setAmount(rs.getFloat("amount"));
                tmp.setApproved(rs.getBoolean("approved"));
                tmp.setPending(rs.getBoolean("pending"));
                tmp.setApprover(rs.getString("approver"));
                tmp.setId(rs.getInt("id"));
                all.add(tmp);

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return all;
        }
    }

    @Override
    public int save(ReimbursementRequest obj) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into public.reimreqs (requester, amount, scomment, requestorid) values (?,?,?,?)";
        try {
            conn = connectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,obj.getRequester());
            ps.setFloat(2, obj.getAmount());
            ps.setString(3,obj.getComment());
            ps.setInt(4,obj.getRequestorid());
            ps.executeUpdate();
            status = 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return status;
        }
    }

    @Override
    public int update(ReimbursementRequest obj) {
        Connection conn = null;
        int status = 0;
        PreparedStatement ps = null;
        String sql = "UPDATE public.reimreqs SET pending=false, approved=? WHERE id=?";
        try {
            conn = connectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1,obj.isApproved());
            ps.setInt(2,obj.getId());
            ps.executeUpdate();
            status=1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return status;
        }
    }

    public ReimbursementRequest findById(int id){
        Connection conn = null;
        ReimbursementRequest tmp = new ReimbursementRequest();
        String sql = "Select * from public.reimreqs where id="+id+";";
        try {
            conn = connectionUtils.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                tmp.setRequester(rs.getString("requester"));
                tmp.setComment(rs.getString("scomment"));
                tmp.setAmount(rs.getFloat("amount"));
                tmp.setApproved(rs.getBoolean("approved"));
                tmp.setPending(rs.getBoolean("pending"));
                tmp.setApprover(rs.getString("approver"));
                tmp.setId(rs.getInt("id"));
                tmp.setRequestorid(rs.getInt("requestorid"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return tmp;
        }
        }


        @Override
    public ReimbursementRequest findByName(String s) { //won't use this
        return null;
    }
    @Override
    public List<ReimbursementRequest> findAllForName(String s) { //use all for id
        return null;
    }



}
