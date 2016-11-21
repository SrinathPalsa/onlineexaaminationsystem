package com.mankraft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdOptions extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		boolean flag = false;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			String s1 = req.getParameter("ali");
			String s2 = req.getParameter("apw");
			ResultSet rs = st.executeQuery("select * from madmininfo");
			while (rs.next() && flag == false) {
				if (s1.equals(rs.getString(1)) && s2.equals(rs.getString(2))) {
					flag = true;
				}
			}
			if (flag == false) {
				out.println("<html><body background='http://localhost:8089/paper_bg.gif'>");

				out.println("<font size=4 color=red><b>");
				out.println("Sorry U are un authorised USER");
				out.println("<a href='http://localhost:8089/adlogin.html'>Back</a>");
				out.println("<b></font></body></html>");
			} else {
				res.sendRedirect("http://localhost:8089/atopics.html");
			}
			rs.close();
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
