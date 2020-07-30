package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class deleteCar extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");
			
			String querySql = "SELECT * FROM car";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			int carid = Integer.parseInt(req.getParameter("carID"));
			
			querySql = "DELETE FROM car WHERE car_id = '" + carid + "';";
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
