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

public class AQuesucdc extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();

			String questno = req.getParameter("lang");
			char lan = questno.charAt(0);
			char type = questno.charAt(1);
			int val = Integer.parseInt(questno.substring(2));

			String quest = req.getParameter("quest");
			String ch1 = req.getParameter("ch1");
			String ch2 = req.getParameter("ch2");
			String ch3 = req.getParameter("ch3");
			String ch4 = req.getParameter("ch4");
			String ch5 = req.getParameter("ch5");
			String ans = req.getParameter("ans");

			int i = st.executeUpdate("delete from mquestions where (lang='"
					+ lan + "' and questype='" + type + "' and quesno=" + val
					+ ")");

			out.println("<body background='http://localhost:8089/paper_bg.gif'>");
			out.println("<font size=4 color=blue><b><center>");
			out.println("Record deleted Sucessfully");
			out.println("<a href='http://localhost:8089/servlet/AQuesd.class'>");
			out.println("Back..</a></b></font></center></body></html>");
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
