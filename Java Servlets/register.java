package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class register extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			// web
			String uid = req.getParameter("username");
			String fname = req.getParameter("fName");
			String lname = req.getParameter("lName");
			String email = req.getParameter("email");
			String pw = req.getParameter("password");

			String querySql = "INSERT INTO car.user (user_id, first_name, last_name, email, password) VALUES ('"
					+ uid + "', '" + fname + "', '" + lname + "', '" + email + "', '" + pw + "');";
			
			Statement st = connection.createStatement();
			int rs = st.executeUpdate(querySql);
			
			res.sendRedirect("main.html");

		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
