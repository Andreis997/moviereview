<!DOCTYPE html>
<html 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:th="http://www.thymeleaf.org" 
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

	<head>
		<title>Login</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

	<body style="background-color:#ededed;">
		<div style="background-color:#343a40!important;;height:80px;"></div>
		<div class="container-fluid">
			<div class="row col-lg-4 col-lg-offset-4" style="margin-top: 400px;background-color:#fff;padding:20px;border:solid 4px #ddd;">
				<!-- <img th:src="@{/images/login.jpg}" class="img-responsive center-block" width="300" height="300" alt="Logo" /> -->
				<form th:action="@{/login}" method="POST" class="form-signin">
					<h3 class="form-signin-heading" th:text="Login" style="font-size:45px"></h3> <br /> 
					
					<input type="text" id="username" name="username" th:placeholder="Username" class="form-control" style="font-size:35px"/> <br /> 
					
					<input type="password" th:placeholder="Password" id="password" name="password" class="form-control" style="font-size:35px"/> <br />
	
					<div align="center" th:if="${param.error}">
						<p style="font-size: 20; color: #FF1C19;">Email or Password is invalid.</p>
					</div>
					
					<button class="btn btn-lg btn-primary" name="Submit" value="Login" type="Submit" th:text="Login" style="margin-right:10px;font-size:25px"></button>
					<a href="/register" style="font-size:25px">Forgot password?</a>
					
				</form>
			</div>
		</div>
	</body>
</html>