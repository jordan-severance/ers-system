package com.ex.ers.services;

import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.DAO.ReimbursementDAO;
import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO;
    public ReimbursementService(){this.reimbursementDAO = new ReimbursementDAO();}
    public ReimbursementService(ReimbursementDAO reimbursementDAO){
        this.reimbursementDAO = reimbursementDAO;
    }

    public ReimbursementRequest saveNewReimReq (String requester, float amount, String comment, int reqID){
        ReimbursementRequest reimbursementRequest = new ReimbursementRequest();
        reimbursementRequest.setRequester(requester);
        reimbursementRequest.setAmount(amount);
        reimbursementRequest.setComment(comment);
        reimbursementRequest.setRequestorid(reqID);
        this.reimbursementDAO.save(reimbursementRequest);

        return reimbursementRequest;
    }

    public List<ReimbursementRequest> getAll(){
        List<ReimbursementRequest> all = new ArrayList();
        all = reimbursementDAO.findAll();
        return all;
    }


    public List<ReimbursementRequest> getAllForId(int id){
        List<ReimbursementRequest> all = new ArrayList();
        all = reimbursementDAO.findAllByID(id);
        return all;
    }

    public ReimbursementRequest findById(int id){
        ReimbursementRequest tmp = new ReimbursementRequest();
        tmp = reimbursementDAO.findById(id);
        return tmp;
    }


    public int markApproved(ReimbursementRequest reimbursementRequest, boolean approve, String managerName){
        int marked =0;
        ReimbursementRequest markedReim = new ReimbursementRequest();
        markedReim.setId(reimbursementRequest.getId());
        markedReim.setApprover(managerName);
        markedReim.setRequester(reimbursementRequest.getRequester());
        markedReim.setApproved(approve);
        markedReim.setAmount(reimbursementRequest.getAmount());
        markedReim.setComment(reimbursementRequest.getComment());
        markedReim.setRequestorid(reimbursementRequest.getRequestorid());

        marked = reimbursementDAO.update(markedReim);
        return marked;
    }
}
