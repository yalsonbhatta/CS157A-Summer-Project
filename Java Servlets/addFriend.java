package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class addFriend extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Connection to MySql

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			String querySql = "SELECT * FROM friendlist";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);

			String user_id1 = req.getParameter("user_id3");
			String user_id2 = req.getParameter("user_id4");
			String temp1;

			while (rs.next()) {
				// name
				String user_id = rs.getString("user_id");
				String friendList = rs.getString("friendlist_id");

				if (user_id1.equals(user_id)) {
					String querySql3 = "INSERT INTO car.friendlist (friendlist_id, user_id) VALUES ('"+ friendList + "', '" + user_id2 + "');";
					Statement st3 = connection.createStatement();
					int rs3 = st3.executeUpdate(querySql3);
					res.sendRedirect("friendlist.html");
					return;
				}
			}

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