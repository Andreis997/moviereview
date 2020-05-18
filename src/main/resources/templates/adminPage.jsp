<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
}

html {
	box-sizing: border-box;
}
 .navbar {
  min-height: 80px;
}
*, *:before, *:after {
	box-sizing: inherit;
}

.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	margin: 8px;
}

.about-section {
	padding: 5px;
	text-align: center;
	background-color: #337ab7;
	color: white;
}

.container {
	padding: 0 16px;
}

.container::after, .row::after {
	content: "";
	clear: both;
	display: table;
}

.title {
	color: grey;
}
.navbar-brand{
    color: white !important;
    font-size: 2.9em !important;
    
}
#navbarNav {
    color: white !important;
    font-size: 1.9em !important;
    
    }
.card:hover {
	background-color: #DCDCDC;
}

a.custom-card, a.custom-card:hover {
	color: inherit;
	text-decoration: none;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="/home">MovieReview</a>
	  
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item active">
	        <a class="nav-link" href="/home">Popular Movies <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/actors">Popular actors</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/admin">Admin Page</a>
	      </li>
	    </ul>

	  </div>
		
		<form class="form-inline" action="logout">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
		</form>
	</nav>
	
	<h2 style="text-align: center">Admin Page</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-4 d-flex align-items-stretch"
				th:each="user: ${users}">
				<div class="card">
						<div class="card-body container">	
							<h5 class="card-title" th:text="${user.getUsername()}"></h5>
							<h6 class="card-subtitle mb-2 text-muted"
								th:text="${user.getRole()}"></h6>
							<br>
							<div class="btn-group" role="group" aria-label="Basic example">
							
							<form method="POST" th:action="@{/admin(id=${user.getId()},role='Premium')}">
								<button type="submit" class="btn btn-secondary" name="submit" value="value">Make Premium</button>
						    </form>
						    
						    <form method="POST" th:action="@{/admin(id=${user.getId()},role='Admin')}">
								<button type="submit" class="btn btn-secondary" name="submit" value="value">Make Admin</button>
						    </form>
						    
						    <form method="POST" th:action="@{/admin(id=${user.getId()},role='Normal')}">
								<button type="submit" class="btn btn-secondary" name="submit" value="value">Make Normal</button>
						    </form>
						
							
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>