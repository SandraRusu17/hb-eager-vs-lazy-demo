package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to : " + jdbcUrl);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("Connection success!!!");


        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
