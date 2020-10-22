package jm.task.core.jdbc;
import java.sql.SQLException;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

        public static void main(String[] args) throws SQLException {
        UserServiceImpl dao = new UserServiceImpl();

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