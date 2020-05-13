<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
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
@import
	url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);

/* Styling h1 and links
––––––––––––––––––––––––––––––––– */
h1[alt="Simple"] {
	color: white;
}

a[href], a[href]:hover {
	color: grey;
	font-size: 0.5em;
	text-decoration: none
}

.starrating>input {
	display: none;
} /* Remove radio buttons */
.starrating>label:before {
	content: "\f005"; /* Star */
	margin: 2px;
	font-size: 8em;
	font-family: FontAwesome;
	display: inline-block;
}

.starrating>label {
	color: #222222; /* Start color when not clicked */
}

.starrating>input:checked ~ label {
	color: #ffca08;
} /* Set yellow color when star checked */
.starrating>input:hover ~ label {
	color: #ffca08;
} /* Set yellow color when star hover */
</style>
</head>
<body>

	<nav
		class="navbar navbar-light bg-light justify-content-between navbar-dark bg-dark">
		<a class="navbar-brand">MovieReview</a>
		<form class="form-inline" action="search">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>

		<form class="form-inline" action="logout">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
		</form>
	</nav>

	<div class="container bg-faded">
		<h1 class="text-center" th:text="${currentMovie.getTitle()}"></h1>
		<div class="progress">
			<div class="progress-bar bg-success" role="progressbar"
				th:style="${currentMovie.getAverageStyle()}"
				th:aria-valuenow="${currentMovie.getVoteAverage()}"
				aria-valuemin="0" aria-valuemax="60"></div>
		</div>
		<div class="row">
			<div class="col text-center"
				th:text="${currentMovie.getVoteAverage()}">Rating</div>
		</div>
		<hr>
		<div class="row">
			<div class="col">
				<div class="mx-auto w-50 p-3 bg-dark text-white text-center">
					<span th:text="${currentMovie.getOverview()}"></span>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-12">
				<img th:src="${currentMovie.getPosterPath()}" alt="Movie"
					class="card-img-top mx-auto w-50">

			</div>
		</div>
		<hr>
		<h4 class="text-center">Reviews from MovieReview users</h4>
		<div class="row">
			<div th:if='${user.getRole().equals("Premium")}'>
				<div class="col-4 mx-auto"
					th:each="review: ${currentMovie.getReviews()}">
					<div class="card card-body mb-2">
						<h6>Review</h6>
						<code th:text="${review.getContent()}"></code>

					</div>
				</div>
			</div>
			<div th:unless='${user.getRole().equals("Premium")}'>
				<span>Only premium users can see reviews</span>
			</div>
		</div>
		<hr>

		<h4 class="text-center">Reviews from IMDB users</h4>
		<div class="row">
			<div class="col-4 mx-auto"
				th:each="review: ${currentMovie.getExternalReviews()}">
				<div class="card card-body mb-2">
					<h6>Review 1</h6>
					<h6 th:text="${review.getAuthor()}"></h6>
					<code th:text="${review.getContent()}"></code>
				</div>

			</div>


		</div>

		<h4 class="text-center" style="padding-top: 50px">Submit a review</h4>

		<div class="row" style="padding-bottom: 100px">

			<form class="col-md-12" method="POST"
				th:if='${user.getRole().equals("Premium")}'
				th:action="@{/addReview(id=${movieId})}">
				<div class="form-group ">
					<label for="exampleFormControlTextarea1"></label>
					<textarea class="form-control" id="reviewArea" rows="3"
						th:name="review"></textarea>
					<div class="">
						<div
							class="starrating risingstar d-flex justify-content-center flex-row-reverse">
							<input type="radio" id="star5" name="rating" value="5" /><label
								for="star5" title="5 star">5</label> <input type="radio"
								id="star4" name="rating" value="4" /><label for="star4"
								title="4 star">4</label> <input type="radio" id="star3"
								name="rating" value="3" /><label for="star3" title="3 star">3</label>
							<input type="radio" id="star2" name="rating" value="2" /><label
								for="star2" title="2 star">2</label> <input type="radio"
								id="star1" name="rating" value="1" /><label for="star1"
								title="1 star">1</label>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary col-md-12">Submit</button>
			</form>
			<span th:unless='${user.getRole().equals("Premium")}'>Vrei sa
				adaugi un Review? Devino user premium</span>

		</div>

	</div>

</body>