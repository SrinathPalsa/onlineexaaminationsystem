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

public class RPWLogin extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		boolean flag = false;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			ResultSet rs = st
					.executeQuery("select loginid,password from musersinfo");
			String user = req.getParameter("ui");
			String passwd = req.getParameter("pw");
			String s3 = "";
			String s4 = "";
			while (rs.next()) {
				s3 = rs.getString(1);
				s4 = rs.getString(2);
				if (s3.equals(user) && s4.equals(passwd)) {
					res.sendRedirect("http://localhost:8089/instr.html");
					flag = true;
				}
			}
			if (flag == false) {
				out.println("<html><body background='http://localhost:8089/paper_bg.gif'>");
				out.println("<a href=http://localhost:8089/regusers.html>");
				out.println("<font size=4 color=red>");
				out.println("<blink>Sorry U are an un authorised USER</blink>");
				out.println("</font>");
				out.println("</a>");
				out.println("</body></html>");
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
