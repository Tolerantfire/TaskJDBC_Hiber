package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class    UserDaoHibernateImpl implements UserDao {
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
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            util.closeSessionFactory();
        }
        System.out.println("User с именем " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        Session session = util.getSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            User user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            util.closeSessionFactory();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Util util = new Util();
        Session session = util.getSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            list = session.createCriteria(User.class).list();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            util.closeSessionFactory();
        }
        System.out.println(list);
        return  list;
    }


    @Override
    public void cleanUsersTable() {
        List<User> list;
        Util util = new Util();
        Session session = util.getSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            list = session.createCriteria(User.class).list();
            for (User user: list){
                session.delete(user);
            }
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
            util.closeSessionFactory();
        }

    }
}
