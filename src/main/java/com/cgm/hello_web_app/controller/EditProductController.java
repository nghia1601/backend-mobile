package com.cgm.hello_web_app.controller;

import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProduct")
public class EditProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Trích xuất ID của sản phẩm từ yêu cầu
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Lấy chi tiết sản phẩm theo ID từ cơ sở dữ liệu
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductByIds(productId);

        // Đặt đối tượng sản phẩm làm thuộc tính trong yêu cầu
        request.setAttribute("product", product);

        // Chuyển hướng yêu cầu đến trang chỉnh sửa
        request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
    }
}

