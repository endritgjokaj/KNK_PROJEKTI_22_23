package repository;

import service.DBConnection;
import models.Perdoruesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public static void insert(Perdoruesi user) throws SQLException {
        String sql = "INSERT INTO perdoruesit (emri,mbiemri,email,fjalekalimi,mosha,gjinia,adresa,numri_telefonit, admin) VALUES (?, ?, ?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, user.getUsername());
//        statement.setString(2, user.getSaltedHash());
//        statement.setString(3, user.getSalt());
        statement.executeUpdate();
    }

    public static Perdoruesi getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM perdoruesit WHERE emri=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String saltedHash = resultSet.getString("salted_hash");
                String salt = resultSet.getString("salt");
//                return new User(id,username, saltedHash, salt);
            } else {
                return null;
            }
            return null;
        }
    }

    public static void update(Perdoruesi user) throws SQLException {
        String sql = "UPDATE perdoruesit SET firstname=?, lastname=?, age=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setInt(3, user.getAge());
//            statement.setInt(4, user.getId());
            statement.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM perdoruesit WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
