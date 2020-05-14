<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
  integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
  crossorigin="anonymous"></script>
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
.navbar-brand{
    color: white !important;
    font-size: 2.9em !important;
    
}
#navbarNav {
    color: white !important;
    font-size: 1.9em !important;
    
    }
.container::after, .row::after {
	content: "";
	clear: both;
	display: table;
}
 .navbar {
  min-height: 80px;
}
.title {
	color: grey;
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
	        	      <a class="nav-link" href="/admin"
							th:if='${user.getRole().equals("Admin")}'>Admin Page</a>
	      </li>
	    </ul>

	  </div>
		<form class="form-inline" action="search">
			<input class="form-control mr-sm-2" type="search" id="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		
		<form class="form-inline" action="logout">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
		</form>
	</nav>

	<h1 style="text-align: center">Top trending movies</h1>
	<div class="container">
		<div class="row">
			<div class="col-md-4 d-flex align-items-stretch"
				th:each="movie: ${movies}">
				<div class="card">
					<a th:href="@{/movieDetail(id=${movie.getId()})}"
						class="custom-card"> <img th:src="${movie.getPosterPath()}"
						alt="Movie" style="width: 100%" class="card-img-top">
						<div class="card-body container">
							<br>
							<h5 class="card-title" th:text="${movie.getTitle()}"></h5>
							<h6 class="card-subtitle mb-2 text-muted"
								th:text="${movie.getRelease_date()}"></h6>
							<p class="card-text" th:text="${movie.getOverview()}"></p>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<script>
            $("#search").autocomplete({
                source: function (request, response) {
                    let data = {
                        name: request.term,
                    };
                    $.ajax({
                        type: 'GET',
                        contentType: "application/json; charset=utf-8",
                        url: "https://api.themoviedb.org/3/search/movie?api_key=453e1204c2a33c24cb0a877e6c23a1c3&language=en-US&query=" + request.term + "&page=1&include_adult=false",
                        success: function (data) {
                            response(
                                $.map(data.results,
                                    function (item) {
                                        const AC = {};

                                        //autocomplete default values REQUIRED
                                        AC.label = item.original_title;
                                        AC.value = item.original_title;
                                        AC.id = item.id;

                                        return AC
                                    }));
                        }
                    });
                },
                minLength: 3,
                delay: 100,
                select: function (event, ui) {
                	window.location.href = "/movieDetail/?id=" + ui.item.id;
                }
            });
	</script>
</body>
</html>