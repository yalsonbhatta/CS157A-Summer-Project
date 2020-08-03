package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class addCar extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/157a?serverTimezone=UTC", "root", "root");
			
			String querySql = "SELECT * FROM car";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			int carid = Integer.parseInt(req.getParameter("carID"));
			String make = req.getParameter("make");
			String model = req.getParameter("model");
			String price = req.getParameter("price");
			int year = Integer.parseInt(req.getParameter("year"));
			
			querySql = "INSERT INTO car(car_id, make, model, year, price) "
					+ "VALUES ('" + carid + "', '" + make  + "', '" + model  + "', '" + year  + "', '" + price + "');";
			
			Statement nst = connection.createStatement();
			int nrs = nst.executeUpdate(querySql);
			res.sendRedirect("aMain.html");
			
		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
