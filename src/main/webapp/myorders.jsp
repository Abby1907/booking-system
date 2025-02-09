<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders</title>
    <link rel="stylesheet" href="myorders.css">
    <link rel="stylesheet" href="home.css" />
</head>
<body>
    <div class="navbar">
        <h2 class="logo">Booking System</h2>
        <ul class="nav-links">
            <li><a href="home.jsp">Home</a></li>
            <li><a href="myOrders.jsp">My Orders</a></li>
            <li><button class="logout-button" onclick="logout()">Logout</button></li>
        </ul>
    </div>

    <div class="container">
        <h2>My Orders</h2>
        <table id="orders-table">
            <thead>
                <tr>
                    <th>Service</th>
                    <th>Sub-Service</th>
                    <th>Date Slot</th>
                    <th>Address</th>
                    <th>Vendor</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <!-- Orders will be loaded here dynamically -->
            </tbody>
        </table>
    </div>

    <script src="myorders.js"></script>
    <script src="home.js"></script>
</body>
</html>
