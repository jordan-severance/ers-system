package com.ex.ers.services;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.models.Person;

import java.util.List;

public class PersonService {
    private PersonDAO personDAO;
    public PersonService(){this.personDAO = new PersonDAO();}
    public PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public boolean legitName(String s){
        Person person = this.personDAO.findByName(s);
        boolean status = false;
        if(person != null){
            status = true;
        }
        return status;
    }

    public Person loginPerson(String username, String password){
        Person person = new Person();
        person = this.personDAO.findByName(username);
        if (password.equals(person.getPw())){
            return person;
        } else {
            return null;
        }
    }

    public Person findByName(String username){
        Person person = new Person();
        person = this.personDAO.findByName(username);
        return person;
    }


    public List<Person> getAllEmployees(){
        List<Person> everyone = null;
        everyone = this.personDAO.findAll();
        return everyone;
    }

    public Person saveNewUser(String fname, String lname, String address, String jobtitle, String username, String pw){
        Person person = new Person();
//        if(this.personDAO.findByName(username)==null){        //username not in use
            person.setFname(fname);
            person.setLname(lname);
            person.setAddress(address);
            person.setJobTitle(jobtitle);
            person.setUsername(username);
            person.setPw(pw);
            this.personDAO.save(person);
//        }

        return person;
    }

    public Person updateUserInfo(String fname, String lname, String address, String jobtitle, String username, String pw){
        Person person = new Person();
        person = findByName(username);
        person.setFname(fname);
        person.setLname(lname);
        person.setAddress(address);
        person.setJobTitle(jobtitle);
        person.setPw(pw);
        int nothing = this.personDAO.update(person);
        return person;
    }


}

