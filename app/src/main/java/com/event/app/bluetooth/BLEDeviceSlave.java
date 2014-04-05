package com.event.app.bluetooth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
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
 * Created by goof_troop on 3/1/14.
 */
public class BLEDeviceSlave implements DeviceSlave {

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
	public BLEDeviceSlave(BluetoothAdapter mBTAdapter) {

		if (mBTAdapter == null) {
			throw new IllegalArgumentException("mBTAdapter");
		}

		this.mBTAdapter = mBTAdapter;
	}

	/**
	 *
	 */
	@TargetApi(18)
	@SuppressLint("NewApi")
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

			// set device scanning state true
		mBTAdapter.startLeScan(mLeScanCallback);
	}

	/**
	 *
	 */
	@TargetApi(18)
	@SuppressLint("NewApi")
	public void stopDiscovery() {

		// set device scanning state false
		if (mBTAdapter.isEnabled() && mBTAdapter.isDiscovering()) {
			mBTAdapter.stopLeScan(mLeScanCallback);
		}

		if (discoverReceiver != null) {
			LocalBroadcastManager.getInstance(EventApp.getAppContext()).unregisterReceiver(discoverReceiver);
		}
	}

	// Ok, so we need a GATT Server to advirtise and accept incoming connections. For every
	// connection, do we need a client connection thread to exchange data?
	// - What can one do with GATT devices? We connect, read the attributes and get a list of
	// supported GATT services, but what do we do with them?
	// - All activities must be able handle Bluetooth events. Once a user starts Bluetooth,
	// the user should be able to work with and manipulate (start, stop,
	// etc.) the Bluetooth Service.
	// - Do Activities, even though they may be paused or destroyed, get called and do stuff if
	// the Bluetooth service sends out a notification? Register a BroadcastHandler?
	// - How will we control the Bluetooth Service? What sort of controls do we need exactly?
	// What and how will we send over Bluetooth?

	/*
	 * BLUETOOTH LE CALLBACKS
	 */

	/**
	 *
	 */
	@SuppressLint("NewApi")
	private BluetoothAdapter.LeScanCallback mLeScanCallback =
			new BluetoothAdapter.LeScanCallback() {

				@Override
				public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
					// notify of device found
				}
			};

	/**
	 *
	 */
	@SuppressLint("NewApi")
	private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

		/**
		 * Callback indicating when GATT client has connected/disconnected to/from a remote
		 * GATT server.
		 *
		 * @param gatt     GATT client
		 * @param status   Status of the connect or disconnect operation.
		 *                 {@link android.bluetooth.BluetoothGatt#GATT_SUCCESS} if the operation succeeds.
		 * @param newState Returns the new connection state. Can be one of
		 *                 {@link android.bluetooth.BluetoothProfile#STATE_DISCONNECTED} or
		 *                 {@link android.bluetooth.BluetoothProfile#STATE_CONNECTED}
		 */
		@Override
		public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
			super.onConnectionStateChange(gatt, status, newState);
		}

		/**
		 * Callback invoked when the list of remote services, characteristics and descriptors
		 * for the remote device have been updated, ie new services have been discovered.
		 *
		 * @param gatt   GATT client invoked {@link android.bluetooth.BluetoothGatt#discoverServices}
		 * @param status {@link android.bluetooth.BluetoothGatt#GATT_SUCCESS} if the remote device
		 */
		@Override
		public void onServicesDiscovered(BluetoothGatt gatt, int status) {
			super.onServicesDiscovered(gatt, status);
		}

		/**
		 * Callback reporting the result of a characteristic read operation.
		 *
		 * @param gatt           GATT client invoked {@link android.bluetooth.BluetoothGatt#readCharacteristic}
		 * @param characteristic Characteristic that was read from the associated
		 *                       remote device.
		 * @param status         {@link android.bluetooth.BluetoothGatt#GATT_SUCCESS} if the read operation
		 */
		@Override
		public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
			super.onCharacteristicRead(gatt, characteristic, status);
		}

		/**
		 * Callback indicating the result of a characteristic write operation.
		 * <p/>
		 * <p>If this callback is invoked while a reliable write transaction is
		 * in progress, the value of the characteristic represents the value
		 * reported by the remote device. An application should compare this
		 * value to the desired value to be written. If the values don't match,
		 * the application must abort the reliable write transaction.
		 *
		 * @param gatt           GATT client invoked {@link android.bluetooth.BluetoothGatt#writeCharacteristic}
		 * @param characteristic Characteristic that was written to the associated
		 *                       remote device.
		 * @param status         The result of the write operation
		 *                       {@link android.bluetooth.BluetoothGatt#GATT_SUCCESS} if the operation succeeds.
		 */
		@Override
		public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
			super.onCharacteristicWrite(gatt, characteristic, status);
		}

		/**
		 * Callback triggered as a result of a remote characteristic notification.
		 *
		 * @param gatt           GATT client the characteristic is associated with
		 * @param characteristic Characteristic that has been updated as a result
		 */
		@Override
		public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
			super.onCharacteristicChanged(gatt, characteristic);
		}
	};
}
