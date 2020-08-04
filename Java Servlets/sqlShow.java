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
		out.println(docType + "<html>\n" + "<head>"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">"
				+ "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>"
				+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>"
				+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>"
				+ "<title>" + title + "</title></head>\n"
				+ "<body>" 
				+ "<nav class='navbar navbar-expand-lg navbar-dark bg-primary'>" 
				+ "<a class=\"navbar-brand\" href=\"main.html\">Home</a>"
				+ "    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\" aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
				"      <span class=\"navbar-toggler-icon\"></span>\r\n" + 
				"    </button>"
				+ "    <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">\r\n" + 
				"      <ul class=\"navbar-nav mr-auto\">\r\n" + 
				"        <li class=\"nav-item\">\r\n" + 
				"          <a class=\"nav-link\" href=\"wishlist.html\">Wishlist</a>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"nav-item\">\r\n" + 
				"          <a class=\"nav-link\" href=\"friendlist.html\">Friendlist</a>\r\n" + 
				"        </li>\r\n" + 
				"        <li class=\"nav-item\">\r\n" + 
				"          <a class=\"nav-link\" href=\"about.html\">About</a>\r\n" + 
				"        </li>\r\n" + 
				"      </ul>\r\n" + 
				"      <ul class=\"navbar-nav my-2 my-lg-0\">\r\n" + 
				"        <li class=\"nav-item\">\r\n" + 
				"            <a class=\"btn btn-danger my-2 my-sm-0\" href=\"logIn.html\">Logout</a>\r\n" + 
				"        </li>\r\n" + 
				"      </ul>\r\n" + 
				"    </div>"
				+ "</nav>"
				+"<div class='container' >"
				+"<br />"
				+"<div class='jumbotron' >"
				+"<h1 align ='center' >" + title + "</h1>\n");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=UTC", "root","password");

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
				String carId = rs.getString("car_id");
				if(make.equals(i) && (price.substring(0, 1).contentEquals(j) || j.equals("0"))  && k<=year && year<=z) {
				out.println("<hr class=\"my-4\">");
				out.println("Make: " + make + "<br>");
				out.println("Model: " + model + "<br>");
				out.println("Year: " + year + "<br>");
				out.println("Price: " + price + "<br>");
				out.println("Car ID: " + carId + "<br><br>");

				}
			}
            out.println("<a href='main.html'>" +"<button class=\"btn btn-danger\">Go to Home</button>"+ "</a>");
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