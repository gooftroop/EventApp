package com.event.app.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import com.event.app.EventApp;
import com.event.app.handler.AbstractCommunicationHandler;
import com.event.app.handler.DeviceSlave;
import com.event.app.resource.Constants;
import com.event.app.resource.ErrorCode;

import java.util.Set;

import static android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE;
import static com.event.app.resource.Constants.REQUEST_ENABLE_BT;

/**
 * Send/recieve any number of events via bluetooth
 * Share contact info
 * Sync with car
 */
public class BluetoothHandler extends AbstractCommunicationHandler {

	/**
	 *
	 */
	private final BluetoothAdapter mBTAdapter;

	/**
	 *
	 */
	private final Context context;

	/**
	 *
	 */
	private DeviceSlave mBDSlave;

	/*
	 *
	 */
	public BluetoothHandler(Activity a) throws BluetoothException {

		context = EventApp.getAppContext();

		// Check the current Build version of the SDK. If the device supports SDK 18 or later,
		// use getSystemService and the non-static getAdapter. Otherwise, use the static
		// getDefaultAdapter
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {

			// Initializes Bluetooth adapter.
			final BluetoothManager bluetoothManager =
					(BluetoothManager)context.getSystemService(Context.BLUETOOTH_SERVICE);

			mBTAdapter = bluetoothManager.getAdapter();
		} else {
			mBTAdapter = BluetoothAdapter.getDefaultAdapter();
		}

		// getDeaultAdapter returns null. Does API18+ method also?
		if (mBTAdapter == null)
			throw new BluetoothException("Bluetooth" + ErrorCode.SERVICE_NOT_SUPPORTED);

		if (! mBTAdapter.isEnabled()) {

			Intent enableBtIntent = new Intent(ACTION_REQUEST_ENABLE);
			a.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}

		Toast.makeText(EventApp.getAppContext(), Constants.BLUETOOTH_ENABLED,
		               Toast.LENGTH_LONG).show();

		// Use this check to determine whether BLE is supported on the device.  Then you can
		// selectively disable BLE-related features.
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
			Toast.makeText(context, Constants.BLE_NOT_SUPPORTED, Toast.LENGTH_SHORT).show();
			mBDSlave = new BLEDeviceSlave(mBTAdapter);
		} else {
			mBDSlave = new DefaultBluetoothDeviceSlave(mBTAdapter);
		}
	}

	/*
     *
     */
	public void clean() {

		if(mBTAdapter.isEnabled())
			mBTAdapter.disable();

		cancelDiscover();
		// any other cleaning/shutting down?
	}

	/*
	 *
	 */
	public Set<BluetoothDevice> getBondedDevices() {

		return mBTAdapter.getBondedDevices();
	}

	/*
     *
     */
	public void discoverDevices() {

		mBDSlave.discoverDevices();
	}

	/*
	 *
	 */
	public void cancelDiscover() {

		mBDSlave.stopDiscovery();
	}

	/*
	 *
	 */
	public void makeVisible() {

		Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);

		context.startActivity(discoverableIntent);

		/*
		 If you would like to be notified when the discoverable mode has changed, you can register a
		 BroadcastReceiver for the ACTION_SCAN_MODE_CHANGED Intent. This will contain the extra
		 fields EXTRA_SCAN_MODE and EXTRA_PREVIOUS_SCAN_MODE, which tell you the new and old scan
		 mode, respectively. Possible values for each are SCAN_MODE_CONNECTABLE_DISCOVERABLE,
		 SCAN_MODE_CONNECTABLE, or SCAN_MODE_NONE, which indicate that the device is either in
		 discoverable mode, not in discoverable mode but still able to receive connections, or not
		 in discoverable mode and unable to receive connections, respectively.
		 */
	}
}
