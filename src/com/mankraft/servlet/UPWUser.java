package com.mankraft.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UPWUser extends HttpServlet {
	String s1, s2, s3;
	boolean flag = false;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			ResultSet rs = st
					.executeQuery("select loginid,password from musersinfo");
			s1 = req.getParameter("ui");
			s2 = req.getParameter("opw");
			s3 = req.getParameter("npw");
			String s4 = "";
			String s5 = "";
			while (rs.next()) {
				s4 = rs.getString(1);
				s5 = rs.getString(2);
				if (s4.equals(s1) && s5.equals(s2)) {
					int i = st.executeUpdate("update musersinfo set password='"
							+ s3 + "' where loginid='" + s1 + "'");
					res.sendRedirect("http://localhost:8089/instr.html");
					flag = true;
				}
			}
			if (flag = false) {
				out.println("<body bgcolor=lightyellow text=blue>");
				out.println("<a href=http://localhost:8089/regusers.html>");
				out.println("<font size=5px color=red>");
				out.println("Sorry U are un authorised USER");
				out.println("</font>");
				out.println("</a>");
				out.println("</body>");
			}
			rs.close();
			st.close();
			c.close();
		} catch (Exception e) {
			out.println(e.toString());
		}
	}
}
