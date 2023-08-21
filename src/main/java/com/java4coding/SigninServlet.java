package com.java4coding;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SigninServlet extends HttpServlet {
	InputStream inputStream;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// -------------------- properties
		Properties prop = new Properties();
		String propFileName = "config.properties";
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		String mysqlHost = prop.getProperty("mysqlHost");
		// --------------------
		boolean b = false;
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String strNme = req.getParameter("nme");
		String strPwd = req.getParameter("pwd");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver"); //for mysql versions lesser than 8.0
			Class.forName("com.mysql.cj.jdbc.Driver");// for mysql version 8.0, this requires java 1.8
//			con = DriverManager.getConnection("jdbc:mysql://mysql_m2_db_1:3306/study", "cjs99", "1764");
			con = DriverManager.getConnection("jdbc:mysql://" + mysqlHost + ":3306/study", "cjs99", "1764");
			st = con.createStatement();
			rs = st.executeQuery("SELECT NAME,PASSWORD FROM CUSTOMER");
			while (rs.next()) {
				if (strNme.equalsIgnoreCase(rs.getString("NAME"))
						&& strPwd.equalsIgnoreCase(rs.getString("PASSWORD"))) {
					b = true;
					break;
				}
			}
			if (b) {
				pw.println("<html>");
				pw.println("<body bgcolor='green'>");
				pw.println("Hello Mr " + strNme + " you are Welcome");
				pw.println("</body>");
				pw.println("</html>");
			} else {
				pw.println("<html>");
				pw.println("<body bgcolor='red'>");
				pw.println("Hello Mr " + strNme + " you are invalid user");
				pw.println("</body>");
				pw.println("</html>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}