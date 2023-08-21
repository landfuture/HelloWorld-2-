package com.java4coding;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
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
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String strNme = req.getParameter("nme");
		String strPwd = req.getParameter("pwd");
		String strEmail = req.getParameter("email");
		String strPhone = req.getParameter("phone");
		Connection con = null;
		PreparedStatement pst = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver"); //for mysql versions lesser than 8.0
			Class.forName("com.mysql.cj.jdbc.Driver");// for mysql version 8.0, this requires java 1.8
//			con = DriverManager.getConnection("jdbc:mysql://mysql_m2_db_1:3306/study", "cjs99", "1764");
			con = DriverManager.getConnection("jdbc:mysql://" + mysqlHost + ":3306/study", "cjs99", "1764");
			pst = con.prepareStatement("INSERT INTO CUSTOMER(NAME, PASSWORD,EMAIL, PHONE) VALUES (?,?,?,?)");
			pst.setString(1, strNme);
			pst.setString(2, strPwd);
			pst.setString(3, strEmail);
			pst.setString(4, strPhone);
			int i = pst.executeUpdate();
			if (i == 1) {
				pw.println("<html>");
				pw.println("<body bgcolor='green'>");
				pw.println("Hello Mr." + strNme + " Thank You for becoming a member");
				pw.println("</body>");
				pw.println("</html>");
			} else {
				pw.println("<html>");
				pw.println("<body bgcolor='red'>");
				pw.println("Hello Mr." + strNme + " Better luck next time");
				pw.println("</body>");
				pw.println("</html>");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Exception caught " + e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Exception caught " + e);
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
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