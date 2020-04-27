<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	</div>

	<h2 style="text-align: center">Top trending movies</h2>
	<div class="row">
		<div class="column">
			<div class="card">
			
				<c:forEach var="movie" items="${movies}">
					
						<img src="/w3images/team1.jpg" alt="Movie1" style="width: 100%">
						<div class="container">
							<h2>${movie.getName()}</h2>
							
							<p class="title">Movie cast</p>
							<p>Some text that describes the movie</p>
							<p>
								<button class="button">Review</button>
							</p>
						</div>
				</c:forEach>
			</div>
		</div>

		
	</div>

	

</body>
</html>