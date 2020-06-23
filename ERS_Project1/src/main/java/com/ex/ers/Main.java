package com.ex.ers;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.app.Application;
import com.ex.ers.app.ERSApp;
import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.ex.ers.services.PersonService;
import com.ex.ers.services.ReimbursementService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;
import com.google.gson.Gson;



public class Main {
    public static void main(String[] args) throws Exception {
        Application app;
        app = new ERSApp();
        app.run();

        ReimbursementService service = new ReimbursementService();
        PersonService personService = new PersonService();

        String fname = "dude";
        String lname ="Lastly";
        String address = "home";
        String jobtitle="Workaholic";
        String username = "test";
        String pw = "test";

        Float amount = 20f;
        String comment = "a really good reason";
        String requester = "John Lastly";
        int reqID = 4;

        ReimbursementRequest reim = service.saveNewReimReq(requester, amount, comment, reqID);


        Person person = personService.updateUserInfo(fname, lname, address, jobtitle, username, pw);
        System.out.println(person.getFname());
        System.out.println(reim.getRequester()+" "+reim.getComment());


    }
    }
