package com.cgm.hello_web_app.restful_api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List; // Import List
import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

@Path("/products/get")
public class GetProductService {
    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByTitle_JSON(@PathParam("title") String title) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getProductsByTitle(title);
        if (!productList.isEmpty()) {
            return Response.ok(productList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No products found with the given title").build();
        }
    }
}
