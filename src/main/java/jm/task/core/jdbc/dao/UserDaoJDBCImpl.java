package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    private java.sql.Statement statement;

    public UserDaoJDBCImpl() {
        connection = Util.getConnect();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        try {
            Statement create = connection.createStatement();
            create.executeUpdate("CREATE TABLE test.lom (" +
                    "id bigint AUTO_INCREMENT primary key," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName varchar(45) NOT NULL, " +
                    "age tinyint)");
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        String sql = "DROP table test.lom";
        try {
            PreparedStatement drop = connection.prepareStatement(sql);
            drop.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO test.lom (name, lastName, age) values (?, ?, ?);";

        try {
            PreparedStatement save = connection.prepareStatement(sql);
            save.setString(1, name);
            save.setString(2, lastName);
            save.setInt(3, age);
            save.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM test.lom where id = ?";
        try {
            PreparedStatement remove = connection.prepareStatement(sql);
            remove.setLong(1, id);
            remove.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM test.lom";
        List<User> usersList = new LinkedList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));

                usersList.add(user);
                System.out.println(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM test.lom";
        PreparedStatement clean = null;
        try {
            clean = connection.prepareStatement(sql);
            clean.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
