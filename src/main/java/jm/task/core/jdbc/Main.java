package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnect();
        UserDao user1 = new UserDaoJDBCImpl();
        user1.createUsersTable();

        user1.saveUser("Alex", "Lom", (byte) 40);
        user1.saveUser("Serg", "Horoshii", (byte) 25);
        user1.saveUser("Ivan", "Malahov", (byte) 31);
        user1.saveUser("Klim", "Kim", (byte) 38);

        user1.removeUserById(2);

        user1.getAllUsers();
        user1.cleanUsersTable();
        user1.dropUsersTable();
        try {
            Util.getConnect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
