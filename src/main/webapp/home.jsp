<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="home.css" />
</head>
<body>
	<div class="navbar">
		<h2 class="logo">Booking System</h2>
		<ul class="nav-links">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="myorders.jsp">My Orders</a></li>
			<li><button class="logout-button" onclick="logout()">Logout</button></li>
		</ul>
	</div>
	<div class="container">
		<div class="header">
			<h1>Home Page Services</h1>
		</div>
		<div class="services">
			<div class="service" onclick="selectService('AC & Appliances')">AC
				& Appliances Repair</div>
			<div class="service" onclick="selectService('House Cleaning')">House
				Cleaning</div>
			<div class="service"
				onclick="selectService('Vehicle Repair Service')">Vehicle
				Repair Service</div>
			<div class="service" onclick="selectService('Product Pickup & Drop')">Product
				Pickup & Drop</div>
		</div>

		<div id="form-container" class="form-container">
			<h2 id="service-title"></h2>
			<form id="booking-form">
				<div class="form-group">
					<label for="sub-service">Select Sub-Service:</label> <select
						id="sub-service" name="subService" required></select>
				</div>
				<div class="form-group">
					<label for="date-slot">Date/Slot:</label> <input type="date"
						id="date-slot" name="dateSlot" required>
				</div>
				<div class="form-group">
					<label for="address">Address:</label>
					<textarea id="address" name="address" maxlength="100"
						placeholder="Enter your address" required></textarea>
				</div>
				<div class="form-group">
					<label for="vendor">Vendor Selection:</label> <select id="vendor"
						name="vendor">
						<option>Vendor 1</option>
						<option>Vendor 2</option>
						<option>Vendor 3</option>
					</select>
				</div>
				<button type="button" class="button" onclick="addService()">Add
					Service</button>
			</form>
		</div>

		<div id="bill-container" class="bill-container">
			<h2>Selected Services</h2>
			<div id="bill-details"></div>
			<h3 id="total-amount">Total Amount: $0</h3>
			<form>
				<input type="hidden" id="selected-services" name="selectedServices">
				<button type="submit" class="button" onclick="confirmBooking()">Confirm
					Booking</button>
			</form>
		</div>

		<div id="success-message" class="success-message">
			<h2>Service Booked Successfully!</h2>
			<p id="booking-details"></p>
		</div>
	</div>
	<script src="home.js"></script>
</body>
</html>