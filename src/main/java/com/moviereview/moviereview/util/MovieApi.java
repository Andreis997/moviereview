package com.moviereview.moviereview.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviereview.moviereview.model.ExternalReview;
import com.moviereview.moviereview.model.Movie;

public class MovieApi {
	private static final String apiUrl = "https://api.themoviedb.org/3";

	public static List<Movie> getPopularMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(executeRestTemplate("/movie/popular"));
			JsonNode resultsNode = jsonNode.get("results");
			for (JsonNode resultNode : resultsNode) {
				movies.add(new Movie(resultNode.get("id").asInt(),
										resultNode.get("title").textValue(),
										String.valueOf(resultNode.get("vote_average").asDouble()),
										resultNode.get("overview").textValue(),
										resultNode.get("release_date").textValue(),
										resultNode.get("poster_path").textValue()));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	public static Movie getMovie(String id) {
		Movie movie = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode resultNode = mapper.readTree(executeRestTemplate("/movie/" + id));
			
			movie = new Movie(resultNode.get("id").asInt(),
										resultNode.get("title").textValue(),
										String.valueOf(resultNode.get("vote_average").asDouble()),
										resultNode.get("overview").textValue(),
										resultNode.get("release_date").textValue(),
										resultNode.get("poster_path").textValue());
			
			List<ExternalReview> l = new ArrayList<>();
			resultNode = mapper.readTree(executeRestTemplate("/movie/" + id + "/reviews"));
			JsonNode resultsNode = resultNode.get("results");
			for (JsonNode rn : resultsNode)  {
				l.add(new ExternalReview(rn.get("id").asText(), rn.get("author").asText(), rn.get("content").asText(), rn.get("url").asText()));
			}
			movie.setExternalReviews(l);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return movie;
		
	}

	private static String executeRestTemplate(final String path) throws UnsupportedEncodingException {
		Map<String, String> parameters = new HashMap<>();
		return executeRestTemplate(parameters, path);
	}

	private static String executeRestTemplate(Map<String, String> parameters, final String path)
			throws UnsupportedEncodingException {
		parameters.put("api_key", "453e1204c2a33c24cb0a877e6c23a1c3");

		final String uri = apiUrl + path + "?" + ParameterStringBuilder.getParamsString(parameters);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, String.class);
	}
}

class ParameterStringBuilder {
	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}