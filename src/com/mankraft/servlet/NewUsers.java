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

public class NewUsers extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection("jdbc:odbc:online");
			Statement st = c.createStatement();
			String user = req.getParameter("ui");
			String passwd = req.getParameter("pw");
			String fname = req.getParameter("fn");

			String lname = req.getParameter("ln");

			String day = req.getParameter("d");
			String month = req.getParameter("m");
			String year = req.getParameter("y");
			String date = day + "/" + month + "/" + year;

			String gender = req.getParameter("g1");
			String occupation = req.getParameter("o");

			ResultSet rs = st.executeQuery("select loginid from musersinfo");
			boolean exist = false;
			while (rs.next()) {
				if (rs.getString(1).equals(user))
					exist = true;
			}
			out.println("<html>");
			if (exist == false) {
				int i = st.executeUpdate("insert into musersinfo values('"
						+ user + "','" + passwd + "','" + fname + "','" + lname
						+ "','" + date + "','" + gender + "','" + occupation
						+ "')");

				out.println("<body background='http://localhost:8089/paper_bg.gif'>");
				out.println("<form action=http://localhost:8089/regusers.html>");
				out.println("<font size=4 color=blue>");
				out.println(user + " is Created");
				out.println("<br><input type=submit name=b2 value=Next>");
				out.println("</font></form></body>");
			} else {
				out.println("<body background='http://localhost:8089/paper_bg.gif'><font size=4 color=red> Sorry!. An User with "
						+ user
						+ " is already existing.Please Choose another login ID.");
				out.println("<br><a href='http://localhost:8089/newusers.html'>Back..</a>");
				out.println("</font></body>");
			}
			out.println("</html>");
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
