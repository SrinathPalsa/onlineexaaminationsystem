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

public class ASubjd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from msubjectinfo");

			out.println("<html><head>");
			out.println("<script>");
			out.println("function f1()");
			out.println("{");
			out.println("window.location.href='http://localhost:8089/atsubjects.html';");
			out.println("}");
			out.println("</script></head>");

			String code;
			out.println("<body background='http://localhost:8089/paper_bg.gif'>");
			out.println("<form name=f2 method=post action='http://localhost:8089/servlet/Sdelete'>");
			out.println("<font size=4 color=blue><center>");
			out.println("Choose Subject Code <select name=code>");
			while (rs.next()) {
				code = rs.getString(1);
				out.println("<option value='" + code + "'>" + code);
			}
			out.println("</select><br><br></font><font color=blue size=4>");
			out.println("<br><input type=submit value=Delete>");
			out.println("<input type=button value=Back name=b1 onClick='f1()'>");
			out.println("</center></font>");
			out.println("<br><br><font size=4 color=red><b>");
			out.println("<center>Warning:  If U delete the Subject Code");
			out.println("then the corresponding RECORDS will also be DELETED.");
			out.println("<center></b></font></form></body></html>");
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
