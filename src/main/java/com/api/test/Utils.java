package com.api.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	URL obj = null;
	HttpURLConnection connection = null;
	ObjectMapper mapper = null;
	Map<String, Object> treeMap = null;

	public Map<String, Object> mapForJson(String json) {
		/*
		 * This method returns a map with String as key and Object as value that can be
		 * case to respective data type. jackson lib's ObjectMapper is used to convert
		 * input String json data to map
		 */
		try {
			mapper = new ObjectMapper();
			treeMap = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeMap;
	}

	public String jsonResponseAsString(String url) {
		/*
		 * This method returns json response as String making a GET request from
		 * provided url
		 */
		StringBuffer response = null;
		try {
			obj = new URL(url);
			connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

			} else {
				System.out.println("GET request didn't go through,response code:" + responseCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return response.toString();
	}
}
