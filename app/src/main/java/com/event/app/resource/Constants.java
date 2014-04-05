package com.event.app.resource;

/**
 * Created by goof_troop on 2/13/14.
 */
public interface Constants {

	// General constants
	public static final String LOG_NAME = "com.event.app.EventApp";

	// Device Actions
	public static final String BLUETOOTH_DEVICE_FOUND = "com.event.app.bluetooth.service" +
			".BLUETOOTH_DEVICE_FOUND";
	public static final String BLUETOOTH_DISCOVERY_FINISHED = "com.event.app.bluetooth.service" +
			".BLUETOOTH_DISCOVERY_FINISHED";
	public static final String BLE_NOT_SUPPORTED = "BLE is not supported";
	public static final String BLUETOOTH_ENABLED = "Bluetooth now enabled";

	// Data exchange events
	public static final String BLUETOOTH_DATA_WRITE = "com.event.app.service.BLUETOOTH_DATA_WRITE";
	public static final String HTTP_DATA_WRITE      = "com.event.app.service.HTTP_DATA_WRITE";
	public static final String NFC_DATA_WRITE       = "com.event.app.service.NFC_DATA_WRITE";

	// GCM Message codes
	public static final int GCM_REQUEST_DATABASE_UPDATE = 0;
	public static final int GCM_REQUEST_EVENT_NOTIFICATION = 1;
	public static final int GCM_REQUEST_CHAT_NOTIFICATION = 2;
	public static final int GCM_REQUEST_SUBSCRIPTION_UPDATE = 3;

	// Bluetooth Activity Enabling
	public static final int REQUEST_ENABLE_BT = 1;

}
