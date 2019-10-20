<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COMP3095 Assignment 1</title>
<style>
	li {
		margin: 0 1em;
		text-decoration: none;
	}
</style>
</head>
<body>
	<div style="display: flex; flex-direction: row; justify-content: space-between;">
		<ul style="display: flex; flex-direction: row;">
			<li><a href="futureEnhancement.jsp">Tab 1</a></li>
			<li><a href="futureEnhancement.jsp">Tab 2</a></li>
			<li><a href="futureEnhancement.jsp">Tab 3</a></li>
			<li><a href="futureEnhancement.jsp">Tab 4</a></li>
		</ul>
		<div style="float: right;">
			Welcome, ${user.firstName}!
			<form action="Logout" method="post">
				<input type="submit" name="logout" value="Logout" />
			</form>
		</div>
	</div>
</body>
</html>