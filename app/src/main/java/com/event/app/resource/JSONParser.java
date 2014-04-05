package com.event.app.resource;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	private final StringBuilder sb;

	public JSONParser() {

		sb = new StringBuilder();
	}

	public void addData(String data) {
		// do checks
		sb.append(data);
	}

	public JSONObject getJSONObject() {

		try {

			return new JSONObject(sb.toString());

		} catch (JSONException e) {
			// log
			return null;
		}
	}
}
