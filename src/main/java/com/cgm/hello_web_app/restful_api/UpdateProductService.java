package com.cgm.hello_web_app.restful_api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

@Path("/products/update")
public class UpdateProductService {

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int productId, Product product) {
        // Đảm bảo ID của sản phẩm trong dữ liệu JSON trùng với ID trong URL
        if (product.getId() != productId) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Product ID in URL does not match ID in data").build();
        }

        ProductDAO productDAO = new ProductDAO();
        boolean success = productDAO.updateProduct(product);

        if (success) {
            return Response.status(Response.Status.OK).entity("Product updated successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update product").build();
        }
    }
}
