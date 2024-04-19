package com.cgm.hello_web_app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/product_mobleadvanced";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0000";
    private Connection connection;
    

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }
    public AccountDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi kết nối ở đây
        }
    }

    public boolean loginUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // True if there is a match, false otherwise
            }
        }
    }

    public boolean createUser(Account account) throws SQLException {
        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                accounts.add(account);
            }
        }
        return accounts;
    }

}
