package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class updateCar extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/157a?serverTimezone=UTC", "root", "root");
			
			String querySql = "SELECT * FROM car";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			String make = req.getParameter("make");
			String model = req.getParameter("model");
			//String price = req.getParameter("price");
			int year = Integer.parseInt(req.getParameter("year"));
			
			String nMake = req.getParameter("nMake");
			String nModel = req.getParameter("nModel");
			String nPrice = req.getParameter("nPrice");
			int nYear = Integer.parseInt(req.getParameter("nYear"));
			
			while(rs.next()) {
				String a = rs.getString("make");
				String b = rs.getString("model");
				int c = Integer.parseInt(rs.getString("year"));
				String d = rs.getString("price");
				
				/*if(a.equals(make) && (b.substring(0, 1).contentEquals(price) || price.equals("0"))  && year == c) {
					querySql = "UPDATE car SET " + a + " = " + nMake + ", " + b + " = " + nModel + ", " + c + " = " + year
							 + ", " + d + " = " + nPrice ;
					
					st.executeUpdate(querySql);
				} */
				
				if(a.equals(make) && b.equals(model) && year == c) {
					querySql = "UPDATE car "
							+ "SET make = '" + nMake + "' , model = '" + nModel + "' , year = '" + nYear + "', price = '" + nPrice + "' "
									+ "WHERE make = '" + make + "' AND model = '" + model + "' AND year = '" + year + "'";
					
					Statement nst = connection.createStatement();
					int nrs = nst.executeUpdate(querySql);
					res.sendRedirect("main.html");
					return;
				}
			}
			res.sendRedirect("updateCar.html");
			return;
			
		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
