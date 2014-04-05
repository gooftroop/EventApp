package com.event.app.database;

import com.event.app.EventApp;
import com.event.app.network.HttpRequestCallback;
import com.event.app.volley.Response;
import com.event.app.volley.VolleyError;

/**
 * Create callbacks, generate request using DatabaseHelp, handle results
 */
public class RemoteDatabaseHandler {

	//
	private static final String mDATABASE_URL = "";


	/**
	 *
	 */
	public static void updateInternalTable(Table query) {

		Response.Listener successCallback = new Response.Listener() {

			/**
			 * Called when a response is received.
			 *
			 * @param response
			 */
			@Override
			public void onResponse(Object response) {

			}
		};

		Response.ErrorListener errorCallback = new Response.ErrorListener() {

			/**
			 * Callback method that an error has been occurred with the
			 * provided error code and optional user-readable message.
			 *
			 * @param error
			 */
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		};

	}


	/**
	 *
	 */
	public static Table queryRemoteTable(Table query) {

		Response.Listener successCallback = new Response.Listener() {

			/**
			 * Called when a response is received.
			 *
			 * @param response
			 */
			@Override
			public void onResponse(Object response) {

			}
		};

		Response.ErrorListener errorCallback = new Response.ErrorListener() {

			/**
			 * Callback method that an error has been occurred with the
			 * provided error code and optional user-readable message.
			 *
			 * @param error
			 */
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		};

		return null;

	}

	/**
	 *
	 */
	public static void updateRemoteTable(Table query) {

		Response.Listener successCallback = new Response.Listener() {

			/**
			 * Called when a response is received.
			 *
			 * @param response
			 */
			@Override
			public void onResponse(Object response) {

			}
		};

		Response.ErrorListener errorCallback = new Response.ErrorListener() {

			/**
			 * Callback method that an error has been occurred with the
			 * provided error code and optional user-readable message.
			 *
			 * @param error
			 */
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		};

	}

	/**
	 *
	 */
	public static void deleteRemoteTable(Table query) {

		Response.Listener successCallback = new Response.Listener() {

			/**
			 * Called when a response is received.
			 *
			 * @param response
			 */
			@Override
			public void onResponse(Object response) {

			}
		};

		Response.ErrorListener errorCallback = new Response.ErrorListener() {

			/**
			 * Callback method that an error has been occurred with the
			 * provided error code and optional user-readable message.
			 *
			 * @param error
			 */
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		};

	}

	/**
	 *
	 */
	public static void addRemoteTable(Table query) {

		Response.Listener successCallback = new Response.Listener() {

			/**
			 * Called when a response is received.
			 *
			 * @param response
			 */
			@Override
			public void onResponse(Object response) {

			}
		};

		Response.ErrorListener errorCallback = new Response.ErrorListener() {

			/**
			 * Callback method that an error has been occurred with the
			 * provided error code and optional user-readable message.
			 *
			 * @param error
			 */
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		};

	}
}
