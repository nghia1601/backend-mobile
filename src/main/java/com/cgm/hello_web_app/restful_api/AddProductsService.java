package com.cgm.hello_web_app.restful_api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

@Path("/products/add")
public class AddProductsService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        ProductDAO productDAO = new ProductDAO();
        boolean success = productDAO.addProduct(product);

        if (success) {
            return Response.status(Response.Status.CREATED).entity("Product added successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add product").build();
        }
    }
}
