package com.ex.ers.services;

import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.DAO.ReimbursementDAO;
import com.ex.ers.models.ReimbursementRequest;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReimbursementServiceTest {
    List<ReimbursementRequest> requests= new ArrayList();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    ReimbursementDAO mockDAO;

    @InjectMocks
    ReimbursementService service;

    @Before
    public void setUp() throws Exception {
        service = new ReimbursementService(mockDAO);
        ReimbursementRequest tmp1 = new ReimbursementRequest();
        ReimbursementRequest tmp2 = new ReimbursementRequest();
        requests.add(tmp1);
        requests.add(tmp2);


    }

    @Test
    public void shoudldGetAllReims() {
        Mockito.when(mockDAO.findAll()).thenReturn(requests);
        List<ReimbursementRequest> actual = service.getAll();
        Assert.assertArrayEquals(requests.toArray(),actual.toArray());
    }

    @Test
    public void shouldGetAllReimsForID(){
        int id = 1;
        Mockito.when(mockDAO.findAllByID(id)).thenReturn(requests);
        List<ReimbursementRequest> actual = service.getAllForId(id);
        Assert.assertArrayEquals(requests.toArray(),actual.toArray());

    }

    @Test
    public void shouldMarkReimApproved(){
        ReimbursementRequest tmp1 = new ReimbursementRequest();
        Mockito.when(mockDAO.update(tmp1)).thenReturn(1);
        int actual = service.markApproved(tmp1,true,"Billy");
        Assert.assertEquals(0, actual);

    }


    @Test
    public void saveNewReimReq() {
        String comment = "client dinner";
        Float amount = 100.00f;
        String name = "Some Guy";
        int id = 4;

        ReimbursementRequest tmp3 = new ReimbursementRequest();
        tmp3.setComment(comment);
        tmp3.setAmount(amount);
        tmp3.setRequester(name);
        tmp3.setRequestorid(id);


        ReimbursementRequest actual = service.saveNewReimReq(name,amount,comment, id);
        Assert.assertSame(tmp3.getComment(),actual.getComment());

    }

    @Test
    public void shouldFindByID(){
        int id = 3;
        ReimbursementRequest tmp1 = new ReimbursementRequest();
        Mockito.when(mockDAO.findById(id)).thenReturn(tmp1);
        ReimbursementRequest actual = service.findById(id);
        Assert.assertEquals(tmp1,actual);

    }
}