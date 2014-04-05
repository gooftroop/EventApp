package com.event.app.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.event.app.resource.Constants;

import static com.event.app.resource.Constants.REQUEST_ENABLE_BT;

/**
 *
 */
public abstract class AbstractActivity extends Activity {

	protected BroadcastReceiver communicationUpdateReceiver;

	// Service read event keys
	public static final String BLUETOOTH_DATA_READ  = "com.event.app.service.BLUETOOTH_DATA_READ";
	public static final String HTTP_DATA_READ       = "com.event.app.service.HTTP_DATA_READ";
	public static final String NFC_DATA_READ        = "com.event.app.service.NFC_DATA_READ";

	/**
	 * Called when the activity is starting.  This is where most initialization
	 * should go: calling {@link #setContentView(int)} to inflate the
	 * activity's UI, using {@link #findViewById} to programmatically interact
	 * with widgets in the UI, calling
	 * {@link #managedQuery(android.net.Uri, String[], String, String[], String)} to retrieve
	 * cursors for data being displayed, etc.
	 * <p/>
	 * <p>You can call {@link #finish} from within this function, in
	 * which case onDestroy() will be immediately called without any of the rest
	 * of the activity lifecycle ({@link #onStart}, {@link #onResume},
	 * {@link #onPause}, etc) executing.
	 * <p/>
	 * <p><em>Derived classes must call through to the super class's
	 * implementation of this method.  If they do not, an exception will be
	 * thrown.</em></p>
	 *
	 * @param savedInstanceState If the activity is being re-initialized after
	 *                           previously being shut down then this Bundle contains the data it most
	 *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
	 * @see #onStart
	 * @see #onSaveInstanceState
	 * @see #onRestoreInstanceState
	 * @see #onPostCreate
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		configureReceiver();
	}

	/**
	 * Called after {@link #onRestoreInstanceState}, {@link #onRestart}, or
	 * {@link #onPause}, for your activity to start interacting with the user.
	 * This is a good place to begin animations, open exclusive-access devices
	 * (such as the camera), etc.
	 * <p/>
	 * <p>Keep in mind that onResume is not the best indicator that your activity
	 * is visible to the user; a system window such as the keyguard may be in
	 * front.  Use {@link #onWindowFocusChanged} to know for certain that your
	 * activity is visible to the user (for example, to resume a game).
	 * <p/>
	 * <p><em>Derived classes must call through to the super class's
	 * implementation of this method.  If they do not, an exception will be
	 * thrown.</em></p>
	 *
	 * @see #onRestoreInstanceState
	 * @see #onRestart
	 * @see #onPostResume
	 * @see #onPause
	 */
	@Override
	protected void onResume() {

		super.onResume();

		configureReceiver();
	}

	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.  The counterpart to
	 * {@link #onResume}.
	 * <p/>
	 * <p>When activity B is launched in front of activity A, this callback will
	 * be invoked on A.  B will not be created until A's {@link #onPause} returns,
	 * so be sure to not do anything lengthy here.
	 * <p/>
	 * <p>This callback is mostly used for saving any persistent state the
	 * activity is editing, to present a "edit in place" model to the user and
	 * making sure nothing is lost if there are not enough resources to start
	 * the new activity without first killing this one.  This is also a good
	 * place to do things like stop animations and other things that consume a
	 * noticeable amount of CPU in order to make the switch to the next activity
	 * as fast as possible, or to close resources that are exclusive access
	 * such as the camera.
	 * <p/>
	 * <p>In situations where the system needs more memory it may kill paused
	 * processes to reclaim resources.  Because of this, you should be sure
	 * that all of your state is saved by the time you return from
	 * this function.  In general {@link #onSaveInstanceState} is used to save
	 * per-instance state in the activity and this method is used to store
	 * global persistent data (in content providers, files, etc.)
	 * <p/>
	 * <p>After receiving this call you will usually receive a following call
	 * to {@link #onStop} (after the next activity has been resumed and
	 * displayed), however in some cases there will be a direct call back to
	 * {@link #onResume} without going through the stopped state.
	 * <p/>
	 * <p><em>Derived classes must call through to the super class's
	 * implementation of this method.  If they do not, an exception will be
	 * thrown.</em></p>
	 *
	 * @see #onResume
	 * @see #onSaveInstanceState
	 * @see #onStop
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * Called when you are no longer visible to the user.  You will next
	 * receive either {@link #onRestart}, {@link #onDestroy}, or nothing,
	 * depending on later user activity.
	 * <p/>
	 * <p>Note that this method may never be called, in low memory situations
	 * where the system does not have enough memory to keep your activity's
	 * process running after its {@link #onPause} method is called.
	 * <p/>
	 * <p><em>Derived classes must call through to the super class's
	 * implementation of this method.  If they do not, an exception will be
	 * thrown.</em></p>
	 *
	 * @see #onRestart
	 * @see #onResume
	 * @see #onSaveInstanceState
	 * @see #onDestroy
	 */
	@Override
	protected void onStop() {
		super.onStop();

		LocalBroadcastManager.getInstance(this).unregisterReceiver(communicationUpdateReceiver);
	}

	/**
	 * Perform any final cleanup before an activity is destroyed.  This can
	 * happen either because the activity is finishing (someone called
	 * {@link #finish} on it, or because the system is temporarily destroying
	 * this instance of the activity to save space.  You can distinguish
	 * between these two scenarios with the {@link #isFinishing} method.
	 * <p/>
	 * <p><em>Note: do not count on this method being called as a place for
	 * saving data! For example, if an activity is editing data in a content
	 * provider, those edits should be committed in either {@link #onPause} or
	 * {@link #onSaveInstanceState}, not here.</em> This method is usually implemented to
	 * free resources like threads that are associated with an activity, so
	 * that a destroyed activity does not leave such things around while the
	 * rest of its application is still running.  There are situations where
	 * the system will simply kill the activity's hosting process without
	 * calling this method (or any others) in it, so it should not be used to
	 * do things that are intended to remain around after the process goes
	 * away.
	 * <p/>
	 * <p><em>Derived classes must call through to the super class's
	 * implementation of this method.  If they do not, an exception will be
	 * thrown.</em></p>
	 *
	 * @see #onPause
	 * @see #onStop
	 * @see #finish
	 * @see #isFinishing
	 */
	@Override
	protected void onDestroy() {

		super.onDestroy();

		LocalBroadcastManager.getInstance(this).unregisterReceiver(communicationUpdateReceiver);
	}

	/**
	 * Called when an activity you launched exits, giving you the requestCode
	 * you started it with, the resultCode it returned, and any additional
	 * data from it.  The <var>resultCode</var> will be
	 * {@link #RESULT_CANCELED} if the activity explicitly returned that,
	 * didn't return any result, or crashed during its operation.
	 * <p/>
	 * <p>You will receive this call immediately before onResume() when your
	 * activity is re-starting.
	 *
	 * @param requestCode The integer request code originally supplied to
	 *                    startActivityForResult(), allowing you to identify who this
	 *                    result came from.
	 * @param resultCode  The integer result code returned by the child activity
	 *                    through its setResult().
	 * @param data        An Intent, which can return result data to the caller
	 *                    (various data can be attached to Intent "extras").
	 * @see #startActivityForResult
	 * @see #createPendingResult
	 * @see #setResult(int)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// User chose not to enable Bluetooth.
		if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
			// stop Bluetooth service
			return;
		}

		super.onActivityResult(requestCode, resultCode, data);


	}

	/**
	 * Configure the {@link }localBroadcastManager}. In Activities, the local BroadcastReceiver
	 * provides the link between the {@link android.os.AsyncTask} services and the current
	 * Activity. The <code>communicationUpdateReceiver</code> is notified when a device (such as
	 * {@link android.bluetooth.BluetoothDevice}) performs an update that the Activity cares about,
	 * or when data from a service is available for reading.
	 */
	private void configureReceiver() {

		// Register the update for any sort of background task update to this UI
		IntentFilter updateFilter = new IntentFilter(Constants.BLUETOOTH_DISCOVERY_FINISHED);
		updateFilter.addAction(BLUETOOTH_DATA_READ);
		updateFilter.addAction(HTTP_DATA_READ );
		updateFilter .addAction(NFC_DATA_READ);

		// add any other messages

		communicationUpdateReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				String action = intent.getAction();

				if (Constants.BLUETOOTH_DEVICE_FOUND.equals(action)) {

				} else if (Constants.BLUETOOTH_DISCOVERY_FINISHED.equals(action)) {

				} else if (BLUETOOTH_DATA_READ.equals(action)) {

				} else if (HTTP_DATA_READ.equals(action)) {

				} else if (NFC_DATA_READ.equals(action)) {

				}
			}
		};

		LocalBroadcastManager.getInstance(this).registerReceiver(communicationUpdateReceiver, updateFilter);
	}
}
