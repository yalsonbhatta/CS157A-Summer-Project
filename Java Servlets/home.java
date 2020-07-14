package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class home extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		String i = req.getParameter("make");
		String j = req.getParameter("price");
		String k = req.getParameter("year");
		
		PrintWriter out = res.getWriter();
		out.println("Make: " + i);
		out.println("Price: " + j);
		out.println("Year: " + k);
	}
}
