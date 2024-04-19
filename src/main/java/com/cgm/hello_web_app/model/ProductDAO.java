package com.cgm.hello_web_app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	
	public List<Product> getAllProducts(){
		String url, user, password;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql;
		ResultSet rs = null;
		List<Product> list = null;
		
		try {
			//1. connect to DB
			//load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//create a connection to DB
			url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
			user = "root";
			password = "0000";
			conn = DriverManager.getConnection(url, user, password);
			sql = "select * from product";
			//create a prepare statement
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<Product>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				double price = rs.getDouble("price");
				String description = rs.getString("description");
				String category = rs.getString("category");
				String image = rs.getString("image");
				Product product = new Product(id, title, price, description,category,  image);
				list.add(product);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pst.close();
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//2. get all product from table product - database k15-pm06
		return list;
	}
	
	
//	them product
	public boolean addProduct(Product product) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;
	    
	    try {
	        //1. Connect to DB
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);
	        
	        //2. Create SQL statement to add a new product
	        sql = "INSERT INTO product (title, price, description, category, image) VALUES (?, ?, ?, ?, ?)";
	        pst = conn.prepareStatement(sql);
	        pst.setString(1, product.getTitle());
	        pst.setDouble(2, product.getPrice());
	        pst.setString(3, product.getDescription());
	        pst.setString(4, product.getCategory());
	        pst.setString(5, product.getImage());
	        
	        //3. Execute SQL statement
	        int rowsAffected = pst.executeUpdate();
	        
	        //4. Return true if product added successfully, false otherwise
	        return rowsAffected > 0;
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        // Handle exception or rethrow it to caller
	        return false;
	    } finally {
	        //5. Close resources in finally block
	        try {
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	// xoa san pham
	public boolean deleteProduct(int productId) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;

	    try {
	        // Kết nối cơ sở dữ liệu
	    	
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);

	        // SQL để xóa một sản phẩm dựa trên ID của nó
	        sql = "DELETE FROM product WHERE id=?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, productId);

	        // Thực thi câu lệnh SQL
	        int rowsAffected = pst.executeUpdate();

	        // Trả về true nếu sản phẩm được xóa thành công, ngược lại trả về false
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	// sua san pham
	public boolean updateProduct(Product product) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;

	    try {
	        // Kết nối cơ sở dữ liệu
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);

	        // SQL để cập nhật thông tin sản phẩm
	        sql = "UPDATE product SET title=?, price=?, description=?, category=?, image=? WHERE id=?";
	        pst = conn.prepareStatement(sql);
	        pst.setString(1, product.getTitle());
	        pst.setDouble(2, product.getPrice());
	        pst.setString(3, product.getDescription());
	        pst.setString(4, product.getCategory());
	        pst.setString(5, product.getImage());
	        pst.setInt(6, product.getId());

	        // Thực thi câu lệnh SQL
	        int rowsAffected = pst.executeUpdate();

	        // Trả về true nếu sản phẩm được cập nhật thành công, ngược lại trả về false
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	// find product by id
	
	public boolean getProductById(int id, Product product) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;
	    ResultSet rs = null;
	    boolean found = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);
	        sql = "SELECT * FROM product WHERE id=?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, id);
	        rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            String title = rs.getString("title");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");
	            String category = rs.getString("category");
	            String image = rs.getString("image");
	            product.setId(id);
	            product.setTitle(title);
	            product.setPrice(price);
	            product.setDescription(description);
	            product.setCategory(category);
	            product.setImage(image);
	            found = true;
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return found;
	}

	public List<Product> getProductsByTitle(String keyword) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;
	    ResultSet rs = null;
	    List<Product> productList = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);
	        sql = "SELECT * FROM product WHERE title LIKE ?";
	        pst = conn.prepareStatement(sql);
	        pst.setString(1, "%" + keyword + "%");
	        rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String title = rs.getString("title");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");
	            String category = rs.getString("category");
	            String image = rs.getString("image");
	            Product product = new Product(id, title, price, description, category, image);
	            productList.add(product);
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return productList;
	}


	
	public void addProducts(Product product) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;
	    
	    try {
	        //1. Connect to DB
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);
	        
	        //2. Create SQL statement to add a new product
	        sql = "INSERT INTO product (title, price, description, category, image) VALUES (?, ?, ?, ?, ?)";
	        pst = conn.prepareStatement(sql);
	        pst.setString(1, product.getTitle());
	        pst.setDouble(2, product.getPrice());
	        pst.setString(3, product.getDescription());
	        pst.setString(4, product.getCategory());
	        pst.setString(5, product.getImage());
	        
	        //3. Execute SQL statement
	        pst.executeUpdate();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        // Handle exception or rethrow it to caller
	    } finally {
	        //5. Close resources in finally block
	        try {
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void deleteProducts(int productId) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    String sql;

	    try {
	        // Kết nối cơ sở dữ liệu
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);

	        // SQL để xóa một sản phẩm dựa trên ID của nó
	        sql = "DELETE FROM product WHERE id=?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, productId);

	        // Thực thi câu lệnh SQL
	        pst.executeUpdate();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public Product getProductByIds(int productId) {
	    String url, user, password;
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String sql;
	    Product product = null;

	    try {
	        // Kết nối với cơ sở dữ liệu
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        url = "jdbc:mysql://localhost:3306/product_mobleadvanced";
	        user = "root";
	        password = "0000";
	        conn = DriverManager.getConnection(url, user, password);

	        // SQL để lấy sản phẩm dựa trên ID
	        sql = "SELECT * FROM product WHERE id=?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, productId);

	        // Thực thi truy vấn SQL
	        rs = pst.executeQuery();

	        // Kiểm tra xem tập kết quả có dữ liệu không
	        if (rs.next()) {
	            // Trích xuất dữ liệu từ tập kết quả và tạo đối tượng Product
	            String title = rs.getString("title");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");
	            String category = rs.getString("category");
	            String image = rs.getString("image");
	            product = new Product(productId, title, price, description, category, image);
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Đóng các tài nguyên trong khối finally
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return product;
	}

	public static void main(String[] args) {
		ProductDAO productDAO  = new ProductDAO();
		productDAO.getAllProducts();
	}

}