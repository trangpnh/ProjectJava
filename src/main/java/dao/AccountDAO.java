package dao;

import connection.MyConnection;
import model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAO {
    // Dinh nghia: xem, them, sua, xoa

    public Account getById(long id) {
        Account account = null;
        try {
            // Tao ket noi
            Connection conn = MyConnection.getConnection();
            // Chuan bi cau lenh, thuc thi
            String sql = "SELECT * FROM `accounts` WHERE `id` = " + id + " LIMIT 1";
            Statement stmt = conn.createStatement();

            // Ket qua
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setUsername(resultSet.getString("username"));
            }
            // Dong tai nguyen
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account getByUserNameAndPassword(String username, String password) {
        Account account = null;
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("SELECT id, username FROM accounts WHERE username='%s' AND password='%s' LIMIT 1 ",
                    username, password);

            // THUC THI
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setUsername(resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    // Co the lam them phan sua xoa nhe....
}
