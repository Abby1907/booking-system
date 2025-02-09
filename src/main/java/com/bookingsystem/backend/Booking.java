package com.bookingsystem.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/Booking")
public class Booking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
        // Get the logged-in user ID from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.getWriter().write("{\"status\":\"error\", \"message\":\"User not logged in!\"}");
            return;
        }

        // Get booking data from request
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // ✅ Parse the JSON request body
        JSONArray bookingArray;
        try {
            String requestBody = sb.toString().trim();

            // Debugging logs
            System.out.println("Raw request body: " + requestBody);

            // If the request is an array, parse it directly
            if (requestBody.startsWith("[")) {
                bookingArray = new JSONArray(requestBody);
            } else {
                JSONObject jsonObject = new JSONObject(requestBody);
                bookingArray = jsonObject.getJSONArray("bookingData");
                System.out.println("Received bookingData: " + bookingArray);
            }

            System.out.println("Parsed bookingData: " + bookingArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Invalid JSON format!\"}");
            return;
        }




        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "password");

            String sql = "INSERT INTO bookings (user_id, service_name, sub_service_name, date_slot, address, vendor, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < bookingArray.length(); i++) {
                JSONObject booking = bookingArray.getJSONObject(i);
                
                ps.setInt(1, userId);
                ps.setString(2, booking.getString("service"));
                ps.setString(3, booking.getString("subService"));
                ps.setString(4, booking.getString("dateSlot"));
                ps.setString(5, booking.getString("address"));
                ps.setString(6, booking.getString("vendor"));
                ps.setDouble(7, booking.getDouble("amount"));

                ps.addBatch();
            }

            ps.executeBatch();
            con.close();

            response.getWriter().write("{\"status\":\"success\", \"message\":\"Booking successful!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Database error!\"}");
        }
    }
}
