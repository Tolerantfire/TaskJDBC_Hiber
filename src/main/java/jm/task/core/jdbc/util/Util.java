package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private Connection connection;
    private Session session;
    private SessionFactory sessionFactory;


    public Connection getConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true&useSSL=false", USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


}
