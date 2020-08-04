package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class updateConsumer extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/157a?serverTimezone=UTC", "root", "root");
			
			String querySql = "SELECT * FROM consumer";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			String uID = req.getParameter("uID");
			//String username = req.getParameter("username");

			
			String nUsername = req.getParameter("nUsername");

			while(rs.next()) {
				String a = rs.getString("user_id");
				//String b = rs.getString("user_name");

				if(a.equals(uID)) {
					querySql = "UPDATE consumer "
							+ "SET username = '" + nUsername + "'"
									+ "WHERE user_id = " + uID + ";";
					
					Statement nst = connection.createStatement();
					int nrs = nst.executeUpdate(querySql);
					
					querySql = "INSERT INTO admin_updates_consumer(user_id, user_name) "
								+"VALUES (3333, '" + nUsername +"')";
					Statement nst2 = connection.createStatement();
					int nrs2 = nst2.executeUpdate(querySql);
					res.sendRedirect("aMain.html");
					return;
				}
			}
			res.sendRedirect("updateConsumer.html");
			return;
			
		} catch (SQLException se) {
			// jdbc
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
