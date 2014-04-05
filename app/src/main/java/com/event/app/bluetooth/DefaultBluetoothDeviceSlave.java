package com.event.app.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import com.event.app.EventApp;
import com.event.app.handler.DeviceSlave;
import com.event.app.resource.Constants;

/**
 *
 */
public class DefaultBluetoothDeviceSlave implements DeviceSlave {

	/**
	 *
	 */
	private final BluetoothAdapter mBTAdapter;

	/**
	 *
	 */
	private BroadcastReceiver discoverReceiver;

	/**
	 *
	 */
	private static final long SCAN_PERIOD = 10000;

	/**
	 *
	 */
	public DefaultBluetoothDeviceSlave(BluetoothAdapter mBTAdapter) {

		if (mBTAdapter == null) {
			throw new IllegalArgumentException("mBTAdapter");
		}

		this.mBTAdapter = mBTAdapter;
	}

	/**
	 *
	 */
	public void discoverDevices() {

		// Stops scanning after a pre-defined scan period.
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				// set device scanning state false?
				stopDiscovery();

				new DefaultBluetoothDeviceSlave(mBTAdapter).discoverDevices();
			}
		}, SCAN_PERIOD);

		// Safety
		stopDiscovery();

		// Register the BroadcastReceiver
		IntentFilter discoveryfilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		discoveryfilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		discoveryfilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);

		discoverReceiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {

				String action = intent.getAction();

				// When discovery finds a device
				if (BluetoothDevice.ACTION_FOUND.equals(action)) {

					// Get the BluetoothDevice object from the Intent
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

					Intent response = new Intent(Constants.BLUETOOTH_DEVICE_FOUND);
					response.putExtra(BluetoothDevice.EXTRA_DEVICE, device);

					LocalBroadcastManager.getInstance(context).sendBroadcast(response);
				}

				if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

					stopDiscovery();
					Intent msg = new Intent(Constants.BLUETOOTH_DISCOVERY_FINISHED);
					LocalBroadcastManager.getInstance(context).sendBroadcast(msg);
				}
			}
		};

		// Register this receiver to the LocalBroadcastManager
		LocalBroadcastManager.getInstance(EventApp.getAppContext()).registerReceiver(discoverReceiver,
		                                                                             discoveryfilter);

		mBTAdapter.startDiscovery();
	}

	/**
	 *
	 */
	public void stopDiscovery() {

		if (mBTAdapter.isEnabled() &&  mBTAdapter.isDiscovering()) {
			mBTAdapter.cancelDiscovery(); // Does cancelling here break? I think there's a bug...
		}

		if (discoverReceiver != null) {
			LocalBroadcastManager.getInstance(EventApp.getAppContext()).unregisterReceiver(discoverReceiver);
		}
	}

	// - All activities must be able handle Bluetooth events. Once a user starts Bluetooth,
	// the user should be able to work with and manipulate (start, stop,
	// etc.) the Bluetooth Service.
	// - Do Activities, even though they may be paused or destroyed, get called and do stuff if
	// the Bluetooth service sends out a notification? Register a BroadcastHandler?
	// - How will we control the Bluetooth Service? What sort of controls do we need exactly?
	// What and how will we send over Bluetooth?

}
