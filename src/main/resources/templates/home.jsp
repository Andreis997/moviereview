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

*, *:before, *:after {
	box-sizing: inherit;
}

.column {
	float: left;
	width: 33.3%;
	margin-bottom: 16px;
	padding: 0 8px;
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

.button {
	border: none;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: #000;
	text-align: center;
	cursor: pointer;
	width: 100%;
}

.button:hover {
	background-color: #555;
}

@media screen and (max-width: 650px) {
	.column {
		width: 100%;
		display: block;
	}
}
</style>
</head>
<body>

	<div class="about-section">
		<h1>Movie list</h1>
		<a href="<c:url value="/logout" />">Logout</a>
	</div>
	<h2 style="text-align: center">Top trending movies</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-3" th:each="movie: ${movies}">
				<div class="card">
					<img src="${movie.getPosterPath()}" alt="Movie" style="width: 100%"
						class="card-img-top">
					<div class="card-body container">
						<h2 th:text="${movie.getTitle()}"></h2>
						<h2 th:text="${movie.getPosterPath()}"></h2>
						<p class="card-title" th:text="${movie.getRelease_date()}"></p>
						<p class="card-text" th:text="${movie.getOverview()}"></p>
						<p>
							<a href="#" class="btn btn-primary">Review</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>