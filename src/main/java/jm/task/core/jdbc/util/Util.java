package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/test"; //?useSSL=false
    public static Connection connection;
    public static String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {

    }

    public static Connection getConnect() {

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL!");
        }
        return connection;

    }

}
