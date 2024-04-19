package com.cgm.hello_web_app.restful_api;

import com.cgm.hello_web_app.model.Account;
import com.cgm.hello_web_app.model.AccountDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/signup")
public class SignupService {
    private final AccountDAO accountDAO;

    // Constructor that accepts Connection as a parameter
    public SignupService() {
    	// Khởi tạo AccountDAO với kết nối cơ sở dữ liệu
        this.accountDAO = new AccountDAO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(Account account) {
        try {
            if (accountDAO.createUser(account)) {
                return Response.status(Response.Status.CREATED).entity("User created successfully").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create user").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while processing signup").build();
        }
    }
}
