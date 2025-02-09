package com.bookingsystem.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registerForm")
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		PrintWriter out = resp.getWriter(); 
		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String contactNumber = req.getParameter("phoneNumber");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "password");
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO register (name, email, password, address, phoneNumber) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, userName);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, address);
			ps.setString(5, contactNumber);
			
			int count = ps.executeUpdate();
			if (count > 0) {
				resp.setContentType("text/html");
//				out.print("<h3 style='color:green'>User register successfully.</h3>");
//				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
//				rd.include(req, resp);
				resp.sendRedirect("login.jsp");
			} else {
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'>Error to register!</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, resp);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'>Internal error!</h3>" + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		}
	}
}
