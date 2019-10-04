package com.api.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	URL obj = null;
	HttpURLConnection connection = null;
	ObjectMapper mapper = null;
	Map<String, Object> treeMap = null;

	public Map<String, Object> mapForJson(String json) {
		try {
			mapper = new ObjectMapper();
			treeMap = mapper.readValue(json, Map.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeMap;
	}

	public String jsonResponseAsString(String url) {
		StringBuffer response = null;
		try {
			obj = new URL(url);
			connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");
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
			connection.disconnect();
		}
		return response.toString();
	}

	private void findKeys(Map<String, Object> treeMap, Map<String, Object> propsmap, Logger logger) {
		treeMap.forEach((key, value) -> {
			if (value instanceof LinkedHashMap) {
				Map<String, Object> map = (LinkedHashMap) value;
				String effkey = key;
				findKeys(map, propsmap, logger);
			} else {
				String finalkey = key;
				propsmap.putIfAbsent(finalkey, value);
			}

		});

	}
}
