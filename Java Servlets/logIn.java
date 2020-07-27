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

public class logIn extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			String querySql = "SELECT * FROM car.user;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);

			// web
			String wId = req.getParameter("username");
			String wPw = req.getParameter("password");

			while (rs.next()) {
				String id = rs.getString("user_id");
				String pw = rs.getString("password");
				if (wId.equals(id) && wPw.equals(pw)) {
					res.sendRedirect("main.html");
					return;
				}
			}
			res.sendRedirect("logIn.html");
			return;
		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
