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

public class AQuesu extends HttpServlet {
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
			out.println("window.location.href='http://localhost:8089/atquestions.html';");
			out.println("}");
			out.println("</script></head>");
			out.println("<body background='http://localhost:8089/paper_bg.gif'>");
			out.println("<form method=post action='http://localhost:8089/servlet/AQuesuc'>");
			out.println("<font size=4 color=blue><b><center>");
			out.println("Choose Subject Code <br><select name='code'>");
			String val = "", caption = "";
			while (rs.next()) {
				val = rs.getString(1);
				caption = rs.getString(2);
				out.println("<option value='" + val + "'>" + caption);
			}
			out.println("</select><br><br></font>");
			out.println("<br><input type=submit value=Next>  <input type=button value=Back name=b1 onClick=\"f1()\">");
			out.println("</center></form></body></html>");
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
