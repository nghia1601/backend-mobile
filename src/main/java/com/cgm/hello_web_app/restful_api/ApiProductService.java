package com.cgm.hello_web_app.restful_api;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.cgm.hello_web_app.model.Product;
import com.cgm.hello_web_app.model.ProductDAO;

@Path("/products")
//http://localhost:8080/hello-web-app/rest/products =>GET
public class ApiProductService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts_JSON(){
		List<Product> List = null;
		ProductDAO productDAO = new ProductDAO();
		List = productDAO.getAllProducts();
		return List;
	}
}