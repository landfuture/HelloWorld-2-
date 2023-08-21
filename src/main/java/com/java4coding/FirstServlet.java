package com.java4coding;

import java.io.IOException;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

//	@Resource()
//	private java.lang.String maxExemptionsString;


	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)

			throws ServletException, IOException {

		super.service(arg0, arg1);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)

			throws ServletException, IOException {

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		pw.println("<html>");

		pw.println("<body bgcolor='pink'>");

		pw.println("<h1>");

		pw.println("Hello World");

		pw.println("<br>");

		pw.println("This is my first Servlet Program");

		pw.println("</h1>");

		pw.println("</body>");

		pw.println("</html>");

	}

//	@Resource()
//	private int maxExemptions;
	public void doGet(HttpServletRequest req, HttpServletResponse res)

			throws ServletException, IOException {

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		pw.println("<html>");

		pw.println("<body bgcolor='pink'>");

		pw.println("<h1>");

		pw.println("Hello World");
//		pw.println(maxExemptions);

		pw.println("<br>");

//		pw.println(maxExemptionsString);

		pw.println("This is my first Servlet Program - 4");

		pw.println("</h1>");

		pw.println("</body>");

		pw.println("</html>");

	}

}