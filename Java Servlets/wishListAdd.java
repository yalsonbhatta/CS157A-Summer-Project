package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class wishListAdd extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Connection to MySql

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");
			
			String i = req.getParameter("list");
			String j = req.getParameter("aCid");

			String querySql = "INSERT INTO car.wishlist (wishlist_name, car_id) VALUES ('"+ i + "', '" + j + "');";
			
			Statement st = connection.createStatement();
			int rs = st.executeUpdate(querySql);
			
			res.sendRedirect("wishlist.html");
			
			st.close();
			connection.close();
		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
