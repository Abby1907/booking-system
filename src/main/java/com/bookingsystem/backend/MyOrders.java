package com.bookingsystem.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.getWriter().write("{\"status\":\"error\", \"message\":\"User not logged in!\"}");
            return;
        }
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "password");
            
            String sql = "SELECT service_name, sub_service_name, date_slot, address, vendor, amount FROM bookings WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();
            JSONArray ordersArray = new JSONArray();
            
            while (rs.next()) {
                JSONObject order = new JSONObject();
                order.put("service", rs.getString("service_name"));
                order.put("subService", rs.getString("sub_service_name"));
                order.put("dateSlot", rs.getString("date_slot"));
                order.put("address", rs.getString("address"));
                order.put("vendor", rs.getString("vendor"));
                order.put("amount", rs.getDouble("amount"));
                ordersArray.put(order);
            }
            con.close();
            out.write(ordersArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Database error!\"}");
		}
	}
}
