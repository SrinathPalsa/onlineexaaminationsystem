package com.mankraft.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ADD1 extends HttpServlet {
	String str1, str2;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		str1 = req.getParameter("adminid");
		str2 = req.getParameter("adminpw");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			PreparedStatement st = c
					.prepareStatement("delete * from  madmininfo where adminid=? and adminpw=?");
			// Statement st=c.createStatement();
			System.out.println("statement created");
			st.setString(1, str1);
			st.setString(2, str2);
			int k = st.executeUpdate();
			// int
			// i=st.executeUpdate("delete * from madmininfo where adminid='"+str1+"'");
			System.out.println("deleted from database");
			out.println("<body bgcolor=lightyellow><font size=5px color=blue><center>");
			out.println("<br><br>Record Deleted Successfully");
			out.println("<a href='http://localhost:8089/ausersd.html'>Back..</a>");
			out.println("</center></font></body>");
			st.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			out.println(e.toString());
		}
	}
}
