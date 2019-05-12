<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>
	<center style="margin-left: 100px; padding-top: 10px;">
		<div class="container"
			style="background-color: #fbfbfb; min-height: 100%; min-width: 100%">
			<div class="logo" style="display: flex; justify-content: center;">
				<img src="s.jpg" width="250" height="250">
			</div>


		<br/>
			<form action="RequestHandler" method="post">
			Please select one of the preferences
			<input
					type="radio" name="contactpreference" style="margin-left:14px;"
					id="phonenumber" value="phonenumber" onclick="window.location.href='phonenumber.jsp'">Phone Number<input
					class="btn btn-primary" style="margin-left:14px;" type="radio" id="email" value="email" onclick="window.location.href='email.jsp'"/>Email<br/>	
			</form>
				<!-- <a href="index.jsp">Search Keyword</a>
				<a href="pincodes.jsp">Search Pincodes</a>-->
			<br /><br/>

	</center>
	</div>
</body>
</html>