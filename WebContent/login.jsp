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
	<h1>LOGIN</h1>
	<h3 class="text-danger text-center">${message}</h3>
	<form action="Login" method="post" class="form-signin container" style="max-width: 40%;">
		<div style="display: flex; flex-direction: row;">
		  <label for="email">Username:</label> 
		  <input type="email" name="email" class="form-control" required>
	  	</div>
	  	<div style="display: flex; flex-direction: row;">
		  <label for="password">Password:</label> 
		  <input type="password" name="password" class="form-control" required>
	  </div>
	  	<div style="display: flex; flex-direction: row;">
	  		<button type="submit" value="Submit" class="btn-block btn-primary">Submit</button>
	  		<a href="./registration.jsp" class="btn-block btn-primary">Register</a>
	  	</div>
	</form> 
</body>
</html>