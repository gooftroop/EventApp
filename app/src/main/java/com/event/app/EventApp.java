package com.event.app;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.event.app.activity.AbstractActivity;
import com.event.app.volley.AuthFailureError;
import com.event.app.volley.NetworkError;
import com.event.app.volley.NetworkResponse;
import com.event.app.volley.NoConnectionError;
import com.event.app.volley.Request;
import com.event.app.volley.RequestQueue;
import com.event.app.volley.ServerError;
import com.event.app.volley.TimeoutError;
import com.event.app.volley.VolleyError;
import com.event.app.volley.VolleyLog;
import com.event.app.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by goof_troop on 1/30/14.
 */
public class EventApp extends Application {

	// Call this on startup. Load preferences, sync db, setup resources, etc.
	// register battery low, battery ok broadcast recviever

	/**
	 * The Application Context
	 */
	private static Context context;

	/**
	 * Log or request Volley TAG
	 */
	public static final String TAG = "VolleyPatterns";

	/**
	 *
	 */
	public static final int NOTIFICATION_ID = 1;

	/**
	 * Global request queue for Volley
	 */
	private static final RequestQueue mRequestQueue = Volley.newRequestQueue(context);

	/**
	 *
	 */
	public EventApp() {

		if (context == null)
			EventApp.context = getApplicationContext();
	}

	/**
	 *
	 * @return the Application Context
	 * @throws IllegalStateException
	 */
	public static Context getAppContext() throws IllegalStateException {

		if (context == null)
			throw new IllegalStateException();

		return EventApp.context;
	}

	/**
	 * Adds the specified request to the global queue, if tag is specified
	 * then it is used else Default TAG is used.
	 *
	 * @param req
	 * @param tag
	 */
	public static <T> void addToRequestQueue(Request<T> req, String tag) {

		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

		VolleyLog.d("Adding request to queue: %s", req.getUrl());

		mRequestQueue.add(req);
	}

	/**
	 * Adds the specified request to the global queue using the Default TAG.
	 *
	 * @param req
	 */
	public static <T> void addToRequestQueue(Request<T> req) {

		// set the default tag if tag is empty
		req.setTag(TAG);

		mRequestQueue.add(req);
	}

	/**
	 * Cancels all pending requests by the specified TAG, it is important
	 * to specify a TAG so that the pending/ongoing requests can be cancelled.
	 *
	 * @param tag
	 */
	public static void cancelPendingRequests(Object tag) {

		mRequestQueue.cancelAll(tag);
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.

	/**
	 *
	 * @param msg
	 * @param title
	 */
	public static void sendNotification(String msg, String title) {

		NotificationManager mNotificationManager = (NotificationManager)
				context.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
		                                                        new Intent(context,
		                                                                   AbstractActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_stat_gcm)
				.setContentTitle(title)
				.setStyle(new NotificationCompat.BigTextStyle()
						          .bigText(msg))
				.setContentText(msg);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}

	/**
	 *
	 */
	public static String parseNetworkErrorMessage(Exception e) {


		if (e instanceof TimeoutError) {
			return context.getResources().getString(R.string.generic_server_down);
		} else if (isServerProblem(e)) {
			return handleServerError(e, context);
		} else if (isNetworkProblem(e)) {
			return context.getResources().getString(R.string.no_internet);
		} else {
			return context.getResources().getString(R.string.generic_error);
		}

		// do error dialog
	}

	/**
	 * Determines whether the error is related to network
	 * @param error
	 * @return
	 */
	private static boolean isNetworkProblem(Object error) {
		return (error instanceof NetworkError) || (error instanceof NoConnectionError);
	}

	/**
	 * Determines whether the error is related to server
	 * @param error
	 * @return
	 */
	private static boolean isServerProblem(Object error) {
		return (error instanceof ServerError) || (error instanceof AuthFailureError);
	}

	/**
	 * Handles the server error, tries to determine whether to show a stock message or to
	 * show a message retrieved from the server.
	 *
	 * @param err
	 * @param context
	 * @return
	 */
	private static String handleServerError(Object err, Context context) {

		VolleyError error = (VolleyError) err;

		NetworkResponse response = error.networkResponse;

		if (response != null) {

			switch (response.statusCode) {

				case 404:

					return context.getResources().getString(R.string.page_not_found);

				case 422:

					return context.getResources().getString(R.string.failed_to_process_request);
				case 401:

					try {
						// server might return error like this { "error": "Some error occured" }
						// Use "Gson" to parse the result
						HashMap<String, String> result
								= new Gson().fromJson(new String(response.data),
								                      new TypeToken<Map<String, String>>() {}.getType());

						if (result != null && result.containsKey("error")) {
							return result.get("error");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					// invalid request
					return error.getMessage();

				default:

					return context.getResources().getString(R.string.generic_server_down);
			}
		}

		return context.getResources().getString(R.string.generic_error);
	}
}