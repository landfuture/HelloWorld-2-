
package com.java4coding;

 

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public class GetData extends HttpServlet {

        protected void doGet(HttpServletRequest req, HttpServletResponse res)

                        throws ServletException, IOException {

                String enteredName = req.getParameter("firstName");

                String[] states = req.getParameterValues("location");

                res.setContentType("text/html");

                PrintWriter out = res.getWriter();

                out.println("<HTML>");

                out.println("<BODY bgcolor='blue'>");

                out.println("<P>Name Entered: " + enteredName + "</P>");

                out.println("</P><P>Location selected: ");

                for (int i = 0; i < states.length; i++) {

                        out.println(states[i] + "<BR>");

                }

                out.println("</P></BODY>");

                out.println("</HTML>");

                out.close();

        }

}