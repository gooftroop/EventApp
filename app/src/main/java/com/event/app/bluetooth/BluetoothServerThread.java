package com.event.app.bluetooth;

import android.bluetooth.BluetoothAdapter;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.event.app.concurrent.CommunicationSelector;
import com.event.app.resource.ErrorCode;
import com.event.app.resource.States;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3/6/14.
 */
public class BluetoothServerThread implements Runnable {

	public final static String TAG = "BluetoothServerThread";

	private final ExecutorService mBluetoothConnectionPool;
	private final Selector selector;
	private final BluetoothServerSocketChannel server;

	private final Object GATE = new Object();

	private States M_STATE;

	/**
	 *
	 * @param mBAdapter
	 * @throws BluetoothException
	 */
	public BluetoothServerThread(BluetoothAdapter mBAdapter) throws BluetoothException {

		mBluetoothConnectionPool = Executors.newCachedThreadPool();

		try {
			server = BluetoothServerSocketChannel.openBluetoothServer(mBAdapter);
			server.configureBlocking(false);
		} catch (IOException e) {
			Log.e(TAG, "Bluetooth" + ErrorCode.SERVER_CREATION_FAILED + ": " + e.getMessage(), e);
			throw new BluetoothException("Bluetooth" + ErrorCode.SERVER_CREATION_FAILED);
		}

		try {
			selector = CommunicationSelector.open();
		} catch (IOException e) {
			Log.e(TAG, "Bluetooth" + ErrorCode.SELECTOR_CREATION_FAILED + ": " + e.getMessage(), e);
			throw new BluetoothException("Bluetooth" + ErrorCode.SELECTOR_CREATION_FAILED);
		}

		try {
			server.register(selector, SelectionKey.OP_ACCEPT);
		} catch (ClosedChannelException e) {
			// TODO
		}

		M_STATE = States.READY;
	}

	/**
	 *
	 * @return
	 */
	public States getState() {
		return M_STATE;
	}

	/**
	 *
	 */
	public void cancel() {

		if (M_STATE != States.CANCELLED)
			M_STATE = States.CANCELLED;
	}

	/**
	 * Starts executing the active part of the class' code. This method is
	 * called when a thread is started that has been created with a class which
	 * implements {@code Runnable}.
	 */
	@Override
	public void run() {

		// Do we want multiple Bluetooth connections?

		if (M_STATE != States.CANCELLED)
			return;

		M_STATE = States.LISTENING;

		BluetoothSocket socket = null;

		try {

		while (M_STATE != States.CANCELLED) {

				synchronized (GATE) {

					selector.select();

					Iterator<SelectionKey> itr;
					for(itr = selector.selectedKeys().iterator(); itr.hasNext();) {

						SelectionKey key = itr.next();
						itr.remove();

						if (key.isValid()) {

							if (key.isConnectable()) {
								((BluetoothSocketChannel)key.channel()).finishConnect();
							}

							if (key.isAcceptable()) {

								BluetoothSocketChannel client = server.acceptBluetooth();
								client.configureBlocking(false);
								client.register(selector, SelectionKey.OP_READ);
							}

							if (key.isReadable()) {
								this.read(key);
							}

							if (key.isWritable()) {
								this.write(key);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// notify?
		} catch (ClassCastException e) {

		} finally {

			try {

				selector.close();
				server.close();
				M_STATE = States.DEAD;

			} catch (IOException e) {
				// log
			}
		}
	}

	private void read(SelectionKey key) throws IOException {

		BluetoothSocketChannel client = (BluetoothSocketChannel) key.channel();


		// notify top of data

	}

	private void write(SelectionKey key) throws IOException {

		BluetoothSocketChannel client = (BluetoothSocketChannel) key.channel();

		// synchronized?
	}
}
