package com.mankraft.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getLang extends HttpServlet {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:online");
			st = con.createStatement();
			rs = st.executeQuery("select * from msubjectinfo");
			String lang = "";
			while (rs.next()) {
				lang += rs.getString(2) + "@";
			}
			ObjectOutputStream oos = new ObjectOutputStream(
					res.getOutputStream());
			oos.writeObject(lang);
		} catch (Exception e) {

		}
	}
}
