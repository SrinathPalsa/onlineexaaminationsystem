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

public class AUsers extends HttpServlet {
	String s1, s2;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			s1 = req.getParameter("aui");
			s2 = req.getParameter("apw");
			int i = st.executeUpdate("insert into madmininfo values('" + s1
					+ "','" + s2 + "')");
			out.println("<html><body background='http://localhost:8089/paper_bg.gif' text=blue><font size=5px>");
			out.println("New Admin-id   " + s1 + "  is Created");
			out.println("<br><a href=\"http://localhost:8089/aus.html\">Back..</a>");
			out.println("</font></body></html>");
			st.close();
			c.close();
		} catch (Exception e) {
			out.println(e.toString());
		}
	}
}
