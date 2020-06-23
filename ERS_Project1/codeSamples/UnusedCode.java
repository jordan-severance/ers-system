package com.ex.ers.Frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/front_controller")
public class Front_controller extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

//        Student student = gson.fromJson(jsonString, Student.class);
//
//        Step 3 − Serialize Object to JSON
//
//        Use toJson() method to get the JSON string representation of an object.
//
////Object to JSON Conversion
//        jsonString = gson.toJson(student);


        System.out.println(request.toString());
        System.out.println("doPost was called");

        StringBuilder sb1 = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb1.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        System.out.println("sb attempt" + sb1.toString());

        //This is ONE way to redirect from a servlet to an html page
        //response.sendRedirect("employee_homepage.html");

        //This is a ONE way to return a String from a servlet to the front end.
        PrintWriter out = response.getWriter();
        out.print(sb1.toString());

        //Gson


//
//        String title = "Auto Refresh Header Setting";
//        String docType =
//                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//        out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n"+
//                "<body bgcolor = \"#f0f0f0\">\n" +
//                "<h1 align = \"center\">" + title + "</h1>\n" +
//                "<p>Current Time is: " + CT + "</p>\n"
//        );

//        Collection<Part> coll = req.getParts();
//        for(Part p: coll){
//            System.out.println(p.getName() + " " + p.toString());
//        }
//        Map<String, String[]> userInfo = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : userInfo.entrySet()) {
//            System.out.println(entry.getKey() + "/" + Arrays.toString(entry.getValue()));
//        }
//        System.out.println(request.getParameter("username"));
//        System.out.println(request.getParameter("password"));
//
//        Enumeration<String> headerNames1 = request.getHeaderNames();
//        while (headerNames1.hasMoreElements()) {
//            String headerName = headerNames1.nextElement();
//            System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//        }
//
//        Enumeration<String> params = request.getParameterNames();
//        while (params.hasMoreElements()) {
//            String paramName = params.nextElement();
//            System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//        }
//
//        super.doPost(request, response);
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//        }
//
//        Enumeration<String> params2 = request.getParameterNames();
//        while (params.hasMoreElements()) {
//            String paramName = params2.nextElement();
//            System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//        }
        /*
    getParameter() − You call request.getParameter() method to get the value of a form parameter.
    getParameterValues() − Call this method if the parameter appears more than once and returns multiple values, for example checkbox.
    getParameterNames() − Call this method if you want a complete list of all parameters in the current request.
         */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        System.out.println("doGet was called");
        super.doPost(req, resp);
    }
}
