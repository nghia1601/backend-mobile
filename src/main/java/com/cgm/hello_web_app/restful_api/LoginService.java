package com.cgm.hello_web_app.restful_api;

import com.cgm.hello_web_app.model.Account;
import com.cgm.hello_web_app.model.AccountDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/login")
public class LoginService {
    private final AccountDAO accountDAO;

    // Constructor mặc định
    public LoginService() {
        // Khởi tạo AccountDAO với kết nối cơ sở dữ liệu
        this.accountDAO = new AccountDAO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Account account) {
        try {
            if (accountDAO.loginUser(account.getUsername(), account.getPassword())) {
                return Response.status(Response.Status.OK).entity("Login successful").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while processing login").build();
        }
    }
}
