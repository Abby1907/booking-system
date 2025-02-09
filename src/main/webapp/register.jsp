<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="register.css">

</head>
<body>
	<form action="registerForm" method="post">
		<label>User Name</label>
		<input type="text" name="userName" required="required" />
		<label>Email</label>
		<input type="email" name="email" required="required" />
		<label>Password</label>
		<input type="password" name="password" required="required" />
		<label>Address</label>
		<input type="text" name="address" />
		<label>Contact Number</label>
		<input type="number" name="phoneNumber" />
		<input type="submit" value="Register" />
	</form>
</body>
</html>