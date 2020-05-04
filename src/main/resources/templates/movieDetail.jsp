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
</style>
</head>
<body>

<nav class="navbar navbar-light bg-light justify-content-between navbar-dark bg-dark" >
  <a class="navbar-brand">MovieReview</a>
	<form class="form-inline" action="search">
		<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form>
	
	<form class="form-inline" action="logout">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
	</form>
</nav>

<div class="container bg-faded">
    <h1 class="text-center" th:text="${currentMovie.getTitle()}"></h1>
    <div class="row">
        <div class="col text-center">Rating</div>
    </div>
    <hr>
    <div class="row">
        <div class="col">
            <div class="mx-auto w-50 p-3 bg-dark text-white text-center">
                <span>Description</span>
            </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-12">
            <img class="mx-auto d-block" src="//placehold.it/200x150?text=mx-auto">
        </div>
    </div>
    <hr>
     <h4 class="text-center">Reviews from IMDB.</h4>
    <div class="row">
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 1</h6>
                <code>Review detail</code>
            </div>
        </div>
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 2</h6>
                <code>Review detail</code>
            </div>
        </div>
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 3</h6>
                <code>Review detail</code>
            </div>
        </div>
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 4</h6>
                <code>Review detail</code>
            </div>
        </div>
    </div>
    <hr>
    
         <h4 class="text-center">Reviews from MovieReview users.</h4>
    <div class="row">
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 1</h6>
                <code>Review detail</code>
            </div>
        </div>
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 2</h6>
                <code>Review detail</code>
            </div>
        </div>
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 3</h6>
                <code>Review detail</code>
            </div>
        </div>
        <div class="col-4 mx-auto">
            <div class="card card-body mb-2">
                <h6>Review 4</h6>
                <code>Review detail</code>
            </div>
        </div>
    </div>
    
</div>

</body>