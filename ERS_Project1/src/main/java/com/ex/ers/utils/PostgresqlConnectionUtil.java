package com.ex.ers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnectionUtil extends ConnectionUtils {
    static {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public PostgresqlConnectionUtil(){this.schema="public";}
    public PostgresqlConnectionUtil(String url, String username, String password, String schema){
        this.url = url;
        this.username = username;
        this.password = password;
        this.schema=schema;
    }

//    ConnectionUtils connectionUtils = new PostgresqlConnectionUtils(
//            "jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/postgres",
//            "master", "sdd^=fsdf24234","ers");


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres", "bar_guy","bigpass");
//                "jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/postgres","master","sdd^=fsdf24234");
    }
}
