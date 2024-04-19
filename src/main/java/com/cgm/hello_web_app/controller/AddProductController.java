package com.cgm.hello_web_app.controller;

import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nhận thông tin sản phẩm từ form
        String title = request.getParameter("title");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String image = request.getParameter("image");

        // Tạo một đối tượng Product từ thông tin nhận được
        Product product = new Product(0, title, price, description, category, image);

        // Gọi phương thức addProduct() từ lớp DAO để thêm sản phẩm
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProducts(product);
        
        response.sendRedirect("crud");
    }
	
	
}
