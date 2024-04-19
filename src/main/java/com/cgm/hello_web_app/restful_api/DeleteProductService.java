package com.cgm.hello_web_app.restful_api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgm.hello_web_app.model.ProductDAO;

@Path("/products/delete")
public class DeleteProductService {

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int productId) {
        ProductDAO productDAO = new ProductDAO();
        boolean success = productDAO.deleteProduct(productId);

        if (success) {
            return Response.status(Response.Status.OK).entity("Product deleted successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete product").build();
        }
    }
}
