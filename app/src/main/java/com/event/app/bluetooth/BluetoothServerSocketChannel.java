package com.event.app.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;

import com.event.app.concurrent.CommunicationSelectorProvidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.NotYetBoundException;
import java.util.UUID;

/**
 * 3/7/14.
 */
public class BluetoothServerSocketChannel extends ServerSocketChannel {

	public final static String TAG = "BluetoothServerThread";
	public final static String SDP_NAME = "";
	public final static UUID SDP_UUID = UUID.randomUUID();

	private final BluetoothServerSocket server;

	/**
	 *
	 */
	public BluetoothServerSocketChannel(BluetoothAdapter mBAdapter) throws IOException {

		super(new CommunicationSelectorProvidor());

		server = mBAdapter.listenUsingRfcommWithServiceRecord(SDP_NAME, SDP_UUID);
	}

	/**
	 * Return the server-socket assigned this channel, which does not declare
	 * any public methods that are not declared in {@code ServerSocket}.
	 *
	 * @return the server-socket assigned to this channel.
	 */
	@Override
	public ServerSocket socket() {
		return null;
	}

	/**
	 *
	 */
	public BluetoothServerSocket bluetoothServerSocket() {
		return null;
	}

	/**
	 *
	 */
	public static BluetoothServerSocketChannel openBluetoothServer(BluetoothAdapter mBAdapter) throws IOException {
		return new BluetoothServerSocketChannel(mBAdapter);
	}

	/**
	 * Accepts a connection to this server-socket channel.
	 * <p/>
	 * This method returns {@code null} when this channel is non-blocking and no
	 * connection is available, otherwise it blocks until a new connection is
	 * available or an I/O error occurs. The socket channel returned by this
	 * method will always be in blocking mode.
	 * <p/>
	 * This method just executes the same security checks as the {@code
	 * accept()} method of the {@link java.net.ServerSocket} class.
	 *
	 * @return the accepted {@code SocketChannel} instance, or {@code null} if
	 * the channel is non-blocking and no connection is available.
	 * @throws AsynchronousCloseException if this channel is closed by another thread while this method
	 *                                    is in operation.
	 * @throws ClosedByInterruptException if another thread interrupts the calling thread while this
	 *                                    operation is in progress. The interrupt state of the calling
	 *                                    thread is set and the channel is closed.
	 * @throws ClosedChannelException     if this channel is closed.
	 * @throws java.io.IOException        if another I/O error occurs.
	 * @throws NotYetBoundException       if the socket has not yet been bound.
	 */
	@Override
	public SocketChannel accept() throws IOException {
		return null;
	}

	/**
	 *
	 */
	public BluetoothSocketChannel acceptBluetooth() throws IOException {
		return null;
	}

	/**
	 * Implements the closing function of the SelectableChannel. This method is
	 * called from {@code implCloseChannel()}.
	 *
	 * @throws java.io.IOException if an I/O exception occurs.
	 */
	@Override
	protected void implCloseSelectableChannel() throws IOException {

	}

	/**
	 * Implements the configuration of blocking/non-blocking mode.
	 *
	 * @param blocking true for blocking, false for non-blocking.
	 * @throws java.io.IOException if an I/O error occurs.
	 */
	@Override
	protected void implConfigureBlocking(boolean blocking) throws IOException {

	}
}
