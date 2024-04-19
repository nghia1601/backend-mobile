package com.cgm.hello_web_app.restful_api;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgm.hello_web_app.model.Checkout;
import com.cgm.hello_web_app.model.CheckoutDAO;
import com.google.gson.Gson;

@WebServlet("/checkout")
public class CheckoutService extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // su dung phuong thuc getReader cua doi tuong HttpServletRequest
		BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        Checkout checkout = gson.fromJson(requestBody.toString(), Checkout.class);

        boolean success = CheckoutDAO.createCheckout(checkout);

        if (success) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
