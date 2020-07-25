package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class sqlShow extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Connection to MySql

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String title = "Selection Results:";

		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n");

		// data from frontend

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");
			java.util.Date now = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(now.getTime());

			String querySql = "SELECT * FROM car";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			String i = req.getParameter("make");
			String j = req.getParameter("price");
			int k = Integer.parseInt(req.getParameter("sYear"));
			int z = Integer.parseInt(req.getParameter("lYear"));

			while (rs.next()) {
				String make = rs.getString("make");
				String model = rs.getString("model");
				int year = Integer.parseInt(rs.getString("year"));
				String price = rs.getString("price");
				if(make.equals(i) && (price.substring(0, 1).contentEquals(j) || j.equals("0"))  && k<=year && year<=z) {
				out.println("Make: " + make + "<br>");
				out.println("Model: " + model + "<br>");
				out.println("Year: " + year + "<br>");
				out.println("Price: " + price + "<br> + <br>");
				}
			}
			out.println("</body></html>");
			st.close();
			rs.close();
			connection.close();
		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}