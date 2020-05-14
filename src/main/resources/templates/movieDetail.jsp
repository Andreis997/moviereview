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
 .navbar {
  min-height: 80px;
}
.navbar-brand{
    color: white !important;
    font-size: 3.0em !important;
    
}
#navbarNav {
    color: white !important;
    font-size: 4.0em !important;
    
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

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="/home">MovieReview</a>
	  
	  <div class="collapse navbar-collapse " id="navbarNav">
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
		
		<form class="form-inline" action="logout">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
		</form>
	</nav>

	<div class="container bg-faded col-md-8">
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
			<div class="col md-6">
				<div class="card flex-md-row mb-4 box-shadow h-md-250">
		            <div class="card-body d-flex flex-column align-items-start">
		              <strong class="d-inline-block mb-2 text-primary">Description</strong>
			              <!--  <h3 class="mb-0">
			                <a class="text-dark" href="#">Featured post</a>
			              </h3>-->
			              <!--<div class="mb-1 text-muted">Nov 12</div>-->
			              <p class="card-text mb-auto" style="font-size:30px"	 th:text="${currentMovie.getOverview()}"></p>
		            </div>
	            <img class="card-img-right flex-auto d-none d-md-block" th:src="${currentMovie.getPosterPath()}" alt="Movie" style="width: 450px; height: 400px;" data-holder-rendered="true">
	           </div>
			</div>
			
		</div>

		<hr>
		<h4 class="text-center">Reviews from MovieReview users</h4>

		<div th:if='${user.getRole().equals("Premium") or user.getRole().equals("Admin")}'>
			<div class="row">
				<div class="col-4 mx-auto"
					th:each="review: ${currentMovie.getReviews()}">
					<div class="card card-body mb-2">
						<a th:href="@{/deleteReview(id=${review.getId()}, idMovie=${currentMovie.getId()})}" class="btn btn-danger" style="font-size:25px ;color:white"
							th:if='${user.getRole().equals("Admin")}'>DELETE</a>
						<p th:text="${review.getRating()}" style="font-size:25px"></p>
						<p th:text="${review.getContent()}" style="font-size:25px"></p>
					</div>
				</div>
			</div>
		</div>
		<div th:unless='${user.getRole().equals("Premium") or user.getRole().equals("Admin")}'>
			<span>Only premium users can see reviews</span>
		</div>
		<hr>

		<h4 class="text-center">Reviews from IMDB users</h4>
		<div class="row">
			<div class="col-4 mx-auto"
				th:each="review: ${currentMovie.getExternalReviews()}">
				<div class="card card-body mb-2">
					<h6 th:text="${review.getAuthor()}"></h6>
					<p class="comment " style="font-size:25px" th:text="${review.getContent()}"></p>
				</div>

			</div>


		</div>

		<h4 class="text-center" style="padding-top: 50px">Submit a review</h4>

		<div class="row" style="padding-bottom: 100px">

			<form class="col-md-12" method="POST"
				th:if='${user.getRole().equals("Premium") or user.getRole().equals("Admin")}'
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
			<span th:unless='${user.getRole().equals("Premium") or user.getRole().equals("Admin")}'>Vrei sa
				adaugi un Review? Devin-o user premium</span>

		</div>

	</div>
	<script>
		$(document).ready(function() {

			$(".comment").shorten();

			$(".comment-small").shorten({
				showChars : 10
			});

		});
	</script>
	<script>
		(function($) {
			$.fn.shorten = function(settings) {
				"use strict";

				var config = {
					showChars : 100,
					minHideChars : 10,
					ellipsesText : "...",
					moreText : "Read More",
					lessText : "less",
					onLess : function() {
					},
					onMore : function() {
					},
					errMsg : null,
					force : false
				};

				if (settings) {
					$.extend(config, settings);
				}

				if ($(this).data('jquery.shorten') && !config.force) {
					return false;
				}
				$(this).data('jquery.shorten', true);

				$(document).off("click", '.morelink');

				$(document).on({
					click : function() {

						var $this = $(this);
						if ($this.hasClass('less')) {
							$this.removeClass('less');
							$this.html(config.moreText);
							$this.parent().prev().animate({
								'height' : '0' + '%'
							}, function() {
								$this.parent().prev().prev().show();
							}).hide('fast', function() {
								config.onLess();
							});

						} else {
							$this.addClass('less');
							$this.html(config.lessText);
							$this.parent().prev().animate({
								'height' : '100' + '%'
							}, function() {
								$this.parent().prev().prev().hide();
							}).show('fast', function() {
								config.onMore();
							});
						}
						return false;
					}
				}, '.morelink');

				return this
						.each(function() {
							var $this = $(this);

							var content = $this.html();
							var contentlen = $this.text().length;
							if (contentlen > config.showChars
									+ config.minHideChars) {
								var c = content.substr(0, config.showChars);
								if (c.indexOf('<') >= 0) // If there's HTML don't want to cut it
								{
									var inTag = false; // I'm in a tag?
									var bag = ''; // Put the characters to be shown here
									var countChars = 0; // Current bag size
									var openTags = []; // Stack for opened tags, so I can close them later
									var tagName = null;

									for (var i = 0, r = 0; r <= config.showChars; i++) {
										if (content[i] == '<' && !inTag) {
											inTag = true;

											// This could be "tag" or "/tag"
											tagName = content.substring(i + 1,
													content.indexOf('>', i));

											// If its a closing tag
											if (tagName[0] == '/') {

												if (tagName != '/'
														+ openTags[0]) {
													config.errMsg = 'ERROR en HTML: the top of the stack should be the tag that closes';
												} else {
													openTags.shift(); // Pops the last tag from the open tag stack (the tag is closed in the retult HTML!)
												}

											} else {
												// There are some nasty tags that don't have a close tag like <br/>
												if (tagName.toLowerCase() != 'br') {
													openTags.unshift(tagName); // Add to start the name of the tag that opens
												}
											}
										}
										if (inTag && content[i] == '>') {
											inTag = false;
										}

										if (inTag) {
											bag += content.charAt(i);
										} // Add tag name chars to the result
										else {
											r++;
											if (countChars <= config.showChars) {
												bag += content.charAt(i); // Fix to ie 7 not allowing you to reference string characters using the []
												countChars++;
											} else // Now I have the characters needed
											{
												if (openTags.length > 0) // I have unclosed tags
												{
													//console.log('They were open tags');
													//console.log(openTags);
													for (j = 0; j < openTags.length; j++) {
														//console.log('Cierro tag ' + openTags[j]);
														bag += '</' + openTags[j] + '>'; // Close all tags that were opened

														// You could shift the tag from the stack to check if you end with an empty stack, that means you have closed all open tags
													}
													break;
												}
											}
										}
									}
									c = $('<div/>').html(
											bag + '<span class="ellip">'
													+ config.ellipsesText
													+ '</span>').html();
								} else {
									c += config.ellipsesText;
								}

								var html = '<div class="shortcontent">'
										+ c
										+ '</div><div class="allcontent">'
										+ content
										+ '</div><span><a href="javascript://nop/" class="morelink">'
										+ config.moreText + '</a></span>';

								$this.html(html);
								$this.find(".allcontent").hide(); // Hide all text
								$('.shortcontent p:last', $this).css(
										'margin-bottom', 0); //Remove bottom margin on last paragraph as it's likely shortened
							}
						});

			};

		})(jQuery);
	</script>
</body>