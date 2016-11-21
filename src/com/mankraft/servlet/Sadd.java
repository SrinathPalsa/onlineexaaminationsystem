package com.mankraft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sadd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String code, name, temp;
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		temp = req.getParameter("v1");
		code = temp.toUpperCase();
		name = req.getParameter("v2");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			int i = st.executeUpdate("insert into msubjectinfo values('" + code
					+ "','" + name + "')");
			out.println("<body background='http://localhost:8089/paper_bg.gif'>");
			out.println("<center><font size=4 color=blue><b>");
			out.println("<br><br>Record inserted Successfully</b></font><font size=4 color=red>");
			out.println("<br><a href='http://localhost:8089/atsubjects.html'>Back..</a>");
			out.println("</font></center></body>");
			st.close();
			c.close();
		} catch (Exception e) {
			out.println(e.toString());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

}
