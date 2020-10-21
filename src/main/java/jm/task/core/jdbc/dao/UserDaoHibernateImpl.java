package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util util = new Util();
        Session session = util.getSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age INT (3))").executeUpdate();
        session.close();
        util.closeSessionFactory();
    }

    @Override
    public void dropUsersTable() {
        Util util = new Util();
        Session session = util.getSession();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.close();
        util.closeSessionFactory();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        Session session = util.getSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем " + name + " добавлен в базу данных");
        session.close();
        util.closeSessionFactory();
    }

    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        Session session = util.getSession();
        session.beginTransaction();
        User user = new User();
        user.setId(id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        util.closeSessionFactory();
    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> list = new ArrayList<>();
        Session session = util.getSession();
        session.beginTransaction();
        list = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        util.closeSessionFactory();
        System.out.println(list);
        return  list;
    }


    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        List<User> list = new ArrayList<>();
        Session session = util.getSession();
        session.beginTransaction();
        list = session.createCriteria(User.class).list();
        for (User user: list){
            session.delete(user);
        }
        session.getTransaction().commit();
        session.close();
        util.closeSessionFactory();

    }
}
