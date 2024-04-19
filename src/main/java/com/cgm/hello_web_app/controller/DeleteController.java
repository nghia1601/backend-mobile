package com.cgm.hello_web_app.controller;

import com.cgm.hello_web_app.model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nhận id sản phẩm từ request
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Gọi phương thức deleteProduct() từ lớp DAO để xóa sản phẩm
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProducts(productId);

        // Chuyển hướng trở lại trang CRUD
        response.sendRedirect("Crud");
    }
}
