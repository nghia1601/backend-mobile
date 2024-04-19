package com.cgm.hello_web_app.restful_api;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

@Path("/products/get")

public class GetProductService {
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById_JSON(@PathParam("id") int id) {
	    ProductDAO productDAO = new ProductDAO();
	    Product product = new Product();
	    boolean found = productDAO.getProductById(id, product);
	    if (found) {
	        return Response.ok(product).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
	    }
	}
}
