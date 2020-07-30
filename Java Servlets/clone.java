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

public class clone extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			// web
			String cloned = req.getParameter("cloned");
			String clone = req.getParameter("clone");

			Statement st = connection.createStatement();
			String querySql = "SELECT * FROM car.wishlist";
			ResultSet rs = st.executeQuery(querySql);

			while (rs.next()) {
				String original = rs.getString("wishlist_name");
				String carId = rs.getString("car_id");
				if (cloned.equals(original)) {
					Statement st2 = connection.createStatement();
					String querySql2 = "INSERT INTO car.wishlist (wishlist_name, car_id) VALUES ('" + clone + "', '"
							+ carId + "');";
					int rs2 = st2.executeUpdate(querySql2);
				}
			}
			res.sendRedirect("wishlist.html");

		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
