package demo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class friendList extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Connection to MySql

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String title = "Friend List:";

		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/car?serverTimezone=Asia/Hong_Kong", "root", "root");

			String querySql = "SELECT * FROM user";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			String wUser_id = req.getParameter("user_id");

			while (rs.next()) {
				//name
				String lName = rs.getString("last_name");
				String fName = rs.getString("first_name");
				String user_id = rs.getString("user_id");
				if(wUser_id.equals(user_id)) {
					out.println( fName+" "+ lName  + "'s Friend(s)"+ "<br>");
					//list id
					String querySql2 = "SELECT * FROM friendlist";
					Statement st2 = connection.createStatement();
					ResultSet rs2 = st2.executeQuery(querySql2);
						while(rs2.next()) {
							String fdList = rs2.getString("friendlist_id");
							String user_id2 = rs2.getString("user_id");
							if(user_id2.equals(user_id)) {
								//list of friends
								String querySql3 = "SELECT * FROM friendlist";
								Statement st3 = connection.createStatement();
								ResultSet rs3 = st3.executeQuery(querySql3);
								while(rs3.next()) {
									String user_id3 = rs3.getString("user_id");
									String fdList2 = rs3.getString("friendlist_id");
									if(fdList2.equals(fdList)) {
										String querySql4 = "SELECT * FROM user";
										Statement st4 = connection.createStatement();
										ResultSet rs4 = st4.executeQuery(querySql4);
										while(rs4.next()) {
											String lName2 = rs4.getString("last_name");
											String fName2 = rs4.getString("first_name");
											String user_id4 = rs4.getString("user_id");
											if(user_id3.equals(user_id4) && !(user_id4.equals(wUser_id))) {
												out.println(fName2 +" "+ lName2 + "<br>");
											}
											
										}
									}
								}
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