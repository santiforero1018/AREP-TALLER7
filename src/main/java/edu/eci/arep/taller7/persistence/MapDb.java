package edu.eci.arep.taller7.persistence;

import java.sql.*;
import java.util.Optional;

import edu.eci.arep.taller7.Model.User;
import edu.eci.arep.taller7.autentication.Autenticator;

/**
 * Class to map data into MySql Db
 * 
 * @author Santiago Forero Yate
 */
public class MapDb {
    private static final String URL = "jdbc:mysql://mysqldb:3306/AREPdb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static MapDb _instance = getInstance();


    /**
     * Method that execute sql to create an users table and insert  a new user in it.
     * 
     */
    public static void insertProofs() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String create = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL" +
                    ")";
            PreparedStatement crTable = con.prepareStatement(create);
            crTable.executeUpdate();
            String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "user1");
            stmt.setString(2, Autenticator.getInstance().hashPwd("password1"));
            stmt.executeUpdate();
            stmt.setString(1, "user2");
            stmt.setString(2, Autenticator.getInstance().hashPwd("password2"));
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("An error happened trying to execute SQL: " + e.getMessage());
        }

    }

    /**
     * method that returns an user if this exist  on the database or empty otherwise
     * @param name  of the user
     * @param pwd password of the 
     * @return an user, empty  otherwise
     */
    public static Optional<User> getUserFromDb(String  name, String pwd){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String query = "SELECT name, password FROM users WHERE name = ? AND password = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, pwd);
            ResultSet rs = stmt.executeQuery();
            return  rs.next() ? Optional.ofNullable(new User(rs.getString("name"), rs.getString("password"))) : Optional.empty();
        } catch (Exception e) { 
            System.out.println("Can't perform the query: "+e.getMessage());
            return Optional.empty();
        }
    }

    public static MapDb getInstance(){
        return _instance;
    }
}
