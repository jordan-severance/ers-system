package com.ex.ers.app;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.models.Person;
import com.ex.ers.services.PersonService;
import com.ex.ers.utils.ConnectionUtils;
import com.ex.ers.utils.PostgresqlConnectionUtil;

public class ERSApp extends Application{
    ConnectionUtils connectionUtils = new PostgresqlConnectionUtil("jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres", "bar_guy","bigpass", "public");

//            "jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/postgres",
//            "master", "sdd^=fsdf24234","ers");
    PersonDAO personDAO = new PersonDAO();
    PersonService personService = new PersonService(personDAO);



    @Override
    public void run() throws Exception {

    }

    public PersonService getPersonService() {
        return personService;
    }
}
