package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class inquireWlist extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Connection to MySql

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String title = "Wish List:";

		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			String querySql = "SELECT * FROM car.wishlist";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);

			String i = req.getParameter("inquire");

			out.println("<h1>"+ i + "</h1>" + "<br>");

			while (rs.next()) {
				String name = rs.getString("wishlist_name");
				String cId = rs.getString("car_id");
				if (name.equals(i)) {
					
					String carQuery = "SELECT * FROM car.car;";
					Statement st2 = connection.createStatement();
					ResultSet rs2 = st2.executeQuery(carQuery);
					while(rs2.next()) {
						String cId2 = rs2.getString("car_id");
						String make = rs2.getString("make");
						String model = rs2.getString("model");
						int year = Integer.parseInt(rs2.getString("year"));
						if(cId2.equals(cId)) {
						out.println("Make: " + make + "<br>");
						out.println("Model: " + model + "<br>");
						out.println("Year: " + year + "<br> + <br>");
						}
					}
				}
			}
			out.println("<button type=\"submit\" formaction=\"logIn.html\">cancel</button>");
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
