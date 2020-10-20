package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {


    public static void main(String[] args) throws SQLException {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
        dao.saveUser("John", "Doe", (byte) 18);
        dao.saveUser("Mary", "Sue", (byte) 21);
        dao.saveUser("Kate", "Smith", (byte) 25);
        dao.saveUser("Bill", "Butcher", (byte) 29);
        dao.getAllUsers();
        dao.cleanUsersTable();
        dao.dropUsersTable();

    }
}