package com.cgm.hello_web_app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgm.hello_web_app.model.Checkout;
import com.cgm.hello_web_app.model.CheckoutDAO;


@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckoutDAO orderDAO = new CheckoutDAO();
        List<Checkout> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);
        RequestDispatcher rd = request.getRequestDispatcher("/Order.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
