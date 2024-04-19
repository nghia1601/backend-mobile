package com.cgm.hello_web_app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgm.hello_web_app.model.Account;
import com.cgm.hello_web_app.model.AccountDAO;


@WebServlet("/account")
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	AccountDAO accountDAO = new AccountDAO();
        try {
            List<Account> accounts = accountDAO.getAllAccounts();
            request.setAttribute("accounts", accounts);
            RequestDispatcher rd = request.getRequestDispatcher("/Account.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
            // Redirect or forward to an error page
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
