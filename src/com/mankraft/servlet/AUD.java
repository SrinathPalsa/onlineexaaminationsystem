package com.mankraft.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AUD extends HttpServlet {
	String str1 = "";

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		str1 = req.getParameter("user");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			int i = st.executeUpdate("delete from musersinfo where loginid='"
					+ str1 + "'");
			out.println("<body background='http://localhost:8089/paper_bg.gif'><font size=5px color=blue><center>");
			out.println("<br><br>Record Deleted Successfully");
			out.println("<a href='http://localhost:8089/ausersd.html'>Back..</a>");
			out.println("</center></font></body>");
			st.close();
			c.close();
		} catch (Exception e) {
			out.println(e.toString());
		}
	}
}
