package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class removeFriend extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Connection to MySql

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			String querySql = "SELECT * FROM friendlist;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);

			String user_id1 = req.getParameter("user_id1");
			String user_id2 = req.getParameter("user_id2");

			while (rs.next()) {
				// name
				String user_id = rs.getString("user_id");
				String friendList = rs.getString("friendlist_id");
				
				if (user_id1.equals(user_id)) {
					String querySql3 = "SELECT * FROM friendlist;";
					Statement st3 = connection.createStatement();
					ResultSet rs3 = st3.executeQuery(querySql3);
					while (rs3.next()) {
						String user_id3 = rs3.getString("user_id");
						String friendList2 = rs3.getString("friendlist_id");
						if (user_id3.equals(user_id2) && friendList2.equals(friendList)) {
							String querySql2 = "DELETE FROM friendlist WHERE user_id = '" + user_id2
									+ "' AND friendlist_id = '" + friendList2 +"';";
							Statement st2 = connection.createStatement();
							int rs2 = st2.executeUpdate(querySql2);
						}
					}
				}
			}

			res.sendRedirect("friendlist.html");
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