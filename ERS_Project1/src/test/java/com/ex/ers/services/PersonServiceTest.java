package com.ex.ers.services;

import com.ex.ers.DAO.DAOs;
import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.models.Person;
import com.sun.org.apache.xpath.internal.objects.XObject;
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

public class PersonServiceTest {
    List<Person> people = new ArrayList();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    PersonDAO mockPersonDAO;

    @InjectMocks
    PersonService service;


    @Before
    public void setUp() throws Exception {
        service = new PersonService(mockPersonDAO);
        Person tmp1 = new Person();
        Person tmp2 = new Person();
        Person tmp3 = new Person();
        tmp1.setUsername("username");
        tmp1.setPw("pass");
        tmp2.setUsername("next user");
        tmp2.setPw("anotherpass");
        tmp3.setUsername("bloke");
        tmp3.setPw("9010");

        people.add(tmp1);
        people.add(tmp2);
        people.add(tmp3);

    }

    @Test
    public void shouldCheckForExistingUsername() {
        Person tmp1 = new Person();
        tmp1.setUsername("username");
        tmp1.setPw("pass");

        Mockito.when(mockPersonDAO.findByName("username")).thenReturn(tmp1);
        boolean actual = service.legitName("username");
        Assert.assertEquals(true,actual);
    }

    @Test
    public void shouldLoginPerson() {
        Person tmp1 = new Person();
        tmp1.setUsername("username");
        tmp1.setPw("pass");

        Mockito.when(mockPersonDAO.findByName("username")).thenReturn(tmp1);
        Person actual = service.loginPerson("username","pass");
        Assert.assertEquals(tmp1.toString(),actual.toString());
    }

    @Test
    public void shouldGetAll(){
        Mockito.when(mockPersonDAO.findAll()).thenReturn(people);
        List<Person> actual =service.getAllEmployees();
        Assert.assertArrayEquals(people.toArray(),actual.toArray());
    }

    @Test
    public void shouldSaveNewUser(){
        Person tmp1 = new Person();
        tmp1.setFname("fname");
        tmp1.setLname("lname");
        tmp1.setAddress("add");
        tmp1.setJobTitle("job");
        tmp1.setUsername("username");
        tmp1.setPw("pass");

        Mockito.when(mockPersonDAO.save(tmp1)).thenReturn(1);
        Person actual = service.saveNewUser("fname","lname","add","job","username","pass");
        Assert.assertSame(tmp1.getUsername(),actual.getUsername());
    }

//    @Test
//    public void shouldUpdateUser(){
//        Person tmp1 = new Person();
//        tmp1.setId(18);
//        tmp1.setFname("john");
//        tmp1.setLname("smith");
//        tmp1.setAddress("CA");
//        tmp1.setJobTitle("associate");
//        tmp1.setUsername("johnny");
//        tmp1.setPw("pass");
//
//        Mockito.when(mockPersonDAO.update(tmp1)).thenReturn(1);
//        Person actual = service.updateUserInfo(18,"john","smith","CA","associate","johnny","pass");
//        Assert.assertSame(tmp1.getAddress(),actual.getAddress());
//    }
}