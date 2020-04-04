package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private Connection connection;
    private Statement stmt;
    private PreparedStatement ps;
    private PreparedStatement psRename;
    private Server server;

    private class UserData {

        private String login;
        private String password;
        private String nickname;

        public UserData(String login, String password, String nickname) throws SQLException {
            ps = connection.prepareStatement("INSERT INTO registration (login,password,name) VALUES (?,?,?)");
            ps.setString(1,login);
            ps.setString(2,password);
            ps.setString(3,nickname);
            ps.executeUpdate();
        }
    }
    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:Reg.db");
        stmt = connection.createStatement();
    }
    public void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public SimpleAuthService() {
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }/*
        try {
            connection.setAutoCommit(false);

        for (int i = 1; i <= 10; i++) {
            try {
                new UserData("login" + i, "pass" + i, "nick" + i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException {
        ResultSet rs = null;

        rs = stmt.executeQuery("SELECT login, password, name FROM registration");
        while (rs.next()) {
            if (rs.getString("login").equals(login) || rs.getString("password").equals(password)) {
                return rs.getString("name");
            }
        }

        return null;

    }


    @Override
    public boolean registration(String login, String password, String nickname){
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT login, name FROM registration");

        while (rs.next()) {
            if (rs.getString("Login").equals(login) || rs.getString("Name").equals(nickname)) {
                return false;
            }
        }
        new UserData(login,password,nickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void rename(String login, String newName){
        try {
            psRename = connection.prepareStatement("UPDATE registration SET name = ? WHERE login = ?");
            psRename.setString(1,newName);
            psRename.setString(2,login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

/*
    CREATE TABLE Registration (
        Login    TEXT,
        Password TEXT,
        Name     TEXT
);
*/

