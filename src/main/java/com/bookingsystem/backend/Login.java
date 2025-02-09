package com.bookingsystem.backend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginForm")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "password");

            // SQL query to check if user exists with given email and password
            PreparedStatement ps = con.prepareStatement("SELECT * FROM register WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ✅ Login success - Create a session and redirect to home.jsp
                HttpSession session = req.getSession();
                session.setAttribute("userName", rs.getString("name")); // Store user name in session

                resp.sendRedirect("home.jsp"); // Redirect to home page
            } else {
                // ❌ Login failed - Show error message and redirect back to login page
                resp.setContentType("text/html");
                out.print("<h3 style='color:red'>Invalid email or password!</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.include(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            out.print("<h3 style='color:red'>Internal error! Please try again later.</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.include(req, resp);
        }
    }
}
