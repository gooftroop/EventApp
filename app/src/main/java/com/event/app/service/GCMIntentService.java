package com.event.app.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.event.app.EventApp;
import com.event.app.activity.AbstractActivity;
import com.event.app.database.GCMBroadcastReceiver;
import com.event.app.database.LocalDatabaseHandler;
import com.event.app.database.RemoteDatabaseHandler;
import com.event.app.resource.Constants;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.event.app.R;


/**
 *
 */
public class GCMIntentService extends IntentService {

	public static final String TAG = "GCMIntentService";
	public static final String OPERATION = "operation";
	public static final String DB_TARGET = "type";
	public static final String GCM_ERROR_TITLE = "GCM Notification";

	/**
	 *
	 */
	public GCMIntentService() {
		super(TAG);
	}

	/**
	 * This method is invoked on the worker thread with a request to process.
	 * Only one Intent is processed at a time, but the processing happens on a
	 * worker thread that runs independently from other application logic.
	 * So, if this code takes a long time, it will hold up other requests to
	 * the same IntentService, but it will not hold up anything else.
	 * When all requests have been handled, the IntentService stops itself,
	 * so you should not call {@link #stopSelf}.
	 *
	 * @param intent The value passed to {@link
	 *               android.content.Context#startService(android.content.Intent)}.
	 */
	@Override
	protected void onHandleIntent(Intent intent) {

		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);

		if (extras != null && !extras.isEmpty()) {  // has effect of unparcelling Bundle

            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

				EventApp.sendNotification(TAG + ": Send error: " + extras.toString(), GCM_ERROR_TITLE);

			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {

				EventApp.sendNotification(TAG + ": Deleted messages on server: " + extras
						                          .toString(), GCM_ERROR_TITLE);

				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

				int service = (Integer) extras.get(OPERATION);

				// If the message equals db update, kick off update
				// If the message equals notification, send notification

				switch(service) {

					case Constants.GCM_REQUEST_DATABASE_UPDATE:

						RemoteDatabaseHandler conn = new RemoteDatabaseHandler();


						LocalDatabaseHandler db = new LocalDatabaseHandler();

						// send update for anyone who cares

						break;

					case Constants.GCM_REQUEST_CHAT_NOTIFICATION:
						break;
					case Constants.GCM_REQUEST_EVENT_NOTIFICATION:
						break;
					case Constants.GCM_REQUEST_SUBSCRIPTION_UPDATE:
						break;
					default:
						break;
				}

			}
		}

		// Release the wake lock provided by the WakefulBroadcastReceiver.
		GCMBroadcastReceiver.completeWakefulIntent(intent);
	}
}
