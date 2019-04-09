package application.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class DBConnect {

  public Connection connection;

  public Connection createConnection() {

    try {

      forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMS", "root", "password");

//            System.out.println("DB Connection Is Success!");

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    return connection;

  }
}
