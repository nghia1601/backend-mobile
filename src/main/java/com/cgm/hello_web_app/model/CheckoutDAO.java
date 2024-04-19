package com.cgm.hello_web_app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDAO {

    public static boolean createCheckout(Checkout checkout) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pst = null;
        String sqlInsertHoaDon = "INSERT INTO hoadon (hoTenKH, sdt, diaChi, pTTT, tongTien) VALUES (?, ?, ?, ?, ?)";
        String sqlInsertChiTietHoaDon = "INSERT INTO cthoadon (maHD, title, quantity, price) VALUES (?, ?, ?, ?)";

        try {
            // Load trình điều khiển JDBC của MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Xử lý ngoại lệ ClassNotFoundException
            e.printStackTrace();
            return false; // Hoặc thực hiện các hành động phù hợp khác
        }

        try {
            // Kết nối cơ sở dữ liệu
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_mobleadvanced", "root", "0000");
            conn.setAutoCommit(false); // Bật chế độ tự commit

            // Thêm thông tin hóa đơn vào bảng hoadon
            pst = conn.prepareStatement(sqlInsertHoaDon, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, checkout.getHoTenKH());
            pst.setString(2, checkout.getSdt());
            pst.setString(3, checkout.getDiaChi());
            pst.setString(4, checkout.getpTTT());
            pst.setDouble(5, checkout.getTongTien());
            pst.executeUpdate();

            // Lấy mã hóa đơn được sinh tự động
            int maHoaDon = 0;
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                maHoaDon = generatedKeys.getInt(1);
            }

            // Thêm thông tin chi tiết hóa đơn vào bảng chitiethoadon
            pst = conn.prepareStatement(sqlInsertChiTietHoaDon);
            for (CartItem item : checkout.getChitiethoadon()) {
                pst.setInt(1, maHoaDon); // Sử dụng mã hóa đơn từ đối tượng Checkout
                pst.setString(2, item.getTitle());
                pst.setInt(3, item.getQuantity());
                pst.setDouble(4, item.getPrice());
                pst.addBatch();
            }

            pst.executeBatch();

            // Commit các thay đổi vào cơ sở dữ liệu
            conn.commit();
            return true;
        } catch (SQLException e) {
            // Xảy ra lỗi, rollback các thay đổi
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Checkout> getAllOrders() {
        List<Checkout> orders = new ArrayList<>();
        String query = "SELECT * FROM hoadon";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_mobleadvanced", "root", "0000");
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Checkout order = new Checkout();
                order.setMaHD(resultSet.getString("maHD"));
                order.setHoTenKH(resultSet.getString("hoTenKH"));
                order.setSdt(resultSet.getString("sdt"));
                order.setDiaChi(resultSet.getString("diaChi"));
                order.setpTTT(resultSet.getString("pTTT"));
                order.setTongTien(resultSet.getDouble("tongTien"));
                // Lấy danh sách chi tiết hóa đơn và gán vào order
                // order.setChitiethoadon(....);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ tại đây nếu cần
        }

        return orders;
    }
}
