package com.moviereview.moviereview.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieApi {
	private static final String apiUrl = "https://api.themoviedb.org/3";

	public static List<String> getPopularMovies() throws IOException {
		List<String> movies = new ArrayList<String>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(executeRestTemplate("/movie/popular"));
		JsonNode resultsNode = jsonNode.get("results");
		for (JsonNode resultNode : resultsNode) {
			movies.add(resultNode.get("original_title").textValue());
		}

		return movies;
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