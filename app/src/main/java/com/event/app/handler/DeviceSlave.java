package com.event.app.handler;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * 3/1/14.
 */
public interface DeviceSlave {

	/**
	 *
	 */
	public void discoverDevices();

	/**
	 *
	 */
	public void stopDiscovery();
}
