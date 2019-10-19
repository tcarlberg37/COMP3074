<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COMP3095 Assignment 1</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	input, a {
		margin: 1em;
	}
</style>
</head>
<body style="text-align: center;">
	<h1>REGISTRATION PAGE</h1>
	<h3 class="text-danger text-center">${message}</h3>
	<form action="Register" method="post" class="form-signin container" style="max-width: 40%;">
		<div style="display: flex; flex-direction: column; justify-content: center;">
			<div style="display: flex; flex-direction: row;">
			  <input type="text" name="firstName" class="form-control" placeholder="First Name*" value="${firstName}" required>
			  <input type="text" name="lastName" class="form-control" placeholder="Last Name*" value="${lastName}" required>
		  	</div>
			<input type="text" name="address" class="form-control" placeholder="Address*" value="${address}" required>
			<input type="email" name="email" class="form-control" placeholder="Email*" value="${email}" required>
			<input type="password" name="password" class="form-control" placeholder="Password*" required>
			<input type="password" name="confirmPassword" class="form-control" placeholder="Confirm Password*" required>
			<input type="checkbox" name="agreeTerms" required>
			<label for="agreeTerms">I agree to the terms of service</label>
		  	<div style="display: flex; flex-direction: row;">
		  		<button type="submit" value="Register" class="btn-block btn-primary">Register</button>
		  		<a href="./login.jsp" class="btn-block btn-primary">Cancel</a>
		  	</div>
		 </div>
	</form> 
</body>
</html>