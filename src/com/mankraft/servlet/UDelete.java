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

public class UDelete extends HttpServlet {
	String str1;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		str1 = req.getParameter("code");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			int i = st.executeUpdate("delete from madmininfo where sub_code='"
					+ str1 + "'");
			out.println("<body bgcolor=lightyellow><font size=5px color=blue><pre>");
			out.println("<br><br>Record Deleted Successfully");
			out.println("<a href='http://localhost:8089/atsubjects.html'>Back..</a>");
			out.println("</pre></font></body>");
		} catch (Exception e) {
			out.println(e.toString());
		}
	}
}
