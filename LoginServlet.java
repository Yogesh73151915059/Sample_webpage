package com.dreamwell;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw=response.getWriter()
;			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:8080/employee","root","Yogesh@658");
		     String n=request.getParameter("texnam");
		     String p=request.getParameter("texpass");
		     PreparedStatement ps=con.prepareStatement("select uname from login where uname=? and password=?");
		     ps.setString(1, n);
		     ps.setString(2, p);
		     ResultSet rs=ps.executeQuery();
		     if(rs.next()) {
		    	 RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
		    	 rd.forward(request, response);
		     }else {
		    	 pw.println("<font  color=red size=18>Login Failed!<br>");
		    	 pw.println("<a herf=index.jsp>Try again!</a>");
		     }
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
