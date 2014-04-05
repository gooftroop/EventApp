package com.event.app.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.CancellationSignal;

import com.event.app.EventApp;
import com.event.app.volley.DefaultRetryPolicy;
import com.event.app.volley.NetworkResponse;
import com.event.app.volley.ParseError;
import com.event.app.volley.Request;
import com.event.app.volley.Response;
import com.event.app.volley.VolleyError;
import com.event.app.volley.VolleyLog;
import com.event.app.volley.toolbox.HttpHeaderParser;
import com.event.app.volley.toolbox.JsonArrayRequest;
import com.event.app.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by goof_troop on 3/16/14.
 */
public class DatabaseHelper extends SQLiteQueryBuilder {

	// provide functionality to build queries
	// provide global constants

	// Build a Request for the Volley queue

	/**
	 *
	 * @param url
	 * @param projectionIn
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param sortOrder
	 * @param limit
	 * @param cancellationSignal
	 */
	public void query(String url, String[] projectionIn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder,
	                  String limit, CancellationSignal cancellationSignal) {


		// build params


	}

	// Build a Request for the Volley queue

	/**
	 *
	 * @param url
	 * @param projectionIn
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param sortOrder
	 * @param limit
	 */
	public void query(String url, String[] projectionIn, String selection, String[] selectionArgs,  String groupBy, String having,
	                  String sortOrder, String limit) {

		// build params

	}

	/**
	 *
	 */
	private void makeRequest(final String url, final String query, final Response.Listener onSuccess, Response.ErrorListener onError) {


		JsonRequest<JSONArray> req = new JsonRequest<JSONArray>(Request.Method.POST, url, query, onSuccess, onError) {

			@Override
			protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {

				try {
					String jsonString = new String(response.data,
					                               HttpHeaderParser.parseCharset(response.headers));

					return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));

				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				} catch (JSONException je) {
					return Response.error(new ParseError(je));
				}
			}
		};

		req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));

		EventApp.addToRequestQueue(req);

	}
}
