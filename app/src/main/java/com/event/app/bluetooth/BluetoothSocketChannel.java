package com.event.app.bluetooth;

import android.bluetooth.BluetoothSocket;

import com.event.app.concurrent.CommunicationSelectorProvidor;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.channels.NoConnectionPendingException;
import java.nio.channels.NotYetConnectedException;

/**
 * 3/7/14.
 */
public class BluetoothSocketChannel extends SocketChannel {

	/**
	 *
	 */
	public BluetoothSocketChannel() {

		super(new CommunicationSelectorProvidor());
	}

	/**
	 * Returns the socket assigned to this channel, which does not declare any public
	 * methods that are not declared in {@code Socket}.
	 *
	 * @return the socket assigned to this channel.
	 */
	@Override
	public Socket socket() {
		return null;
	}

	/**
	 * Returns the bluetooth socket assigned to this channel, which does not declare any public
	 * methods that are not declared in {@code BluetoothSocket}.
	 *
	 * @return the bluetooth socket assigned to this channel.
	 */
	public BluetoothSocket bluetoothSocket() {
		return null;
	}

	/**
	 * Indicates whether this channel's socket is connected.
	 *
	 * @return {@code true} if this channel's socket is connected, {@code false}
	 * otherwise.
	 */
	@Override
	public boolean isConnected() {
		return false;
	}

	/**
	 * Indicates whether this channel's socket is still trying to connect.
	 *
	 * @return {@code true} if the connection is initiated but not finished;
	 * {@code false} otherwise.
	 */
	@Override
	public boolean isConnectionPending() {
		return false;
	}

	/**
	 * Connects this channel's socket with a remote address.
	 * <p/>
	 * If this channel is blocking, this method will suspend until connecting is
	 * finished or an I/O exception occurs. If the channel is non-blocking,
	 * this method will return {@code true} if the connection is finished at
	 * once or return {@code false} when the connection must be finished later
	 * by calling {@code finishConnect()}.
	 * <p/>
	 * This method can be called at any moment and can block other read and
	 * write operations while connecting. It executes the same security checks
	 * as the connect method of the {@code Socket} class.
	 *
	 * @param address the address to connect with.
	 * @return {@code true} if the connection is finished, {@code false}
	 * otherwise.
	 * @throws AlreadyConnectedException       if the channel is already connected.
	 * @throws ConnectionPendingException      a non-blocking connecting operation is already executing on
	 *                                         this channel.
	 * @throws ClosedChannelException          if this channel is closed.
	 * @throws AsynchronousCloseException      if this channel is closed by another thread while this method
	 *                                         is executing.
	 * @throws ClosedByInterruptException      if another thread interrupts the calling thread while this
	 *                                         operation is in progress. The calling thread will have the
	 *                                         interrupt state set and this channel will be closed.
	 * @throws UnresolvedAddressException      if the address is not resolved.
	 * @throws UnsupportedAddressTypeException if the address type is not supported.
	 * @throws java.io.IOException             if an I/O error occurs.
	 */
	@Override
	public boolean connect(SocketAddress address) throws IOException {
		return false;
	}

	/**
	 * Completes the connection process initiated by a call of {@code
	 * connect(SocketAddress)}.
	 * <p/>
	 * This method returns {@code true} if the connection is finished already
	 * and returns {@code false} if the channel is non-blocking and the
	 * connection is not finished yet.
	 * <p/>
	 * If this channel is in blocking mode, this method will suspend and return
	 * {@code true} when the connection is finished. It closes this channel and
	 * throws an exception if the connection fails.
	 * <p/>
	 * This method can be called at any moment and it can block other {@code
	 * read} and {@code write} operations while connecting.
	 *
	 * @return {@code true} if the connection is successfully finished, {@code
	 * false} otherwise.
	 * @throws NoConnectionPendingException if the channel is not connected and the connection process
	 *                                      has not been initiated.
	 * @throws ClosedChannelException       if this channel is closed.
	 * @throws AsynchronousCloseException   if this channel is closed by another thread while this method
	 *                                      is executing.
	 * @throws ClosedByInterruptException   if another thread interrupts the calling thread while this
	 *                                      operation is in progress. The calling thread has the
	 *                                      interrupt state set, and this channel is closed.
	 * @throws java.io.IOException          if an I/O error occurs.
	 */
	@Override
	public boolean finishConnect() throws IOException {
		return false;
	}

	/**
	 * Reads bytes from this socket channel into the given buffer.
	 * <p/>
	 * The maximum number of bytes that will be read is the remaining number of
	 * bytes in the buffer when the method is invoked. The bytes will be copied
	 * into the buffer starting at the buffer's current position.
	 * <p/>
	 * The call may block if other threads are also attempting to read from this
	 * channel.
	 * <p/>
	 * Upon completion, the buffer's position is set to the end of the bytes
	 * that have been read. The buffer's limit is not changed.
	 *
	 * @param target the byte buffer to receive the bytes.
	 * @return the number of bytes actually read.
	 * @throws AsynchronousCloseException if another thread closes the channel during the read.
	 * @throws NotYetConnectedException   if this channel is not yet connected.
	 * @throws ClosedByInterruptException if another thread interrupts the calling thread while this
	 *                                    operation is in progress. The interrupt state of the calling
	 *                                    thread is set and the channel is closed.
	 * @throws ClosedChannelException     if this channel is closed.
	 * @throws java.io.IOException        if another I/O error occurs.
	 * @see java.nio.channels.ReadableByteChannel#read(java.nio.ByteBuffer)
	 */
	@Override
	public int read(ByteBuffer target) throws IOException {
		return 0;
	}

	/**
	 * Reads bytes from this socket channel into a subset of the given buffers.
	 * This method attempts to read all {@code remaining()} bytes from {@code
	 * length} byte buffers, in order, starting at {@code targets[offset]}. The
	 * number of bytes actually read is returned.
	 * <p/>
	 * If a read operation is in progress, subsequent threads will block until
	 * the read is completed and will then contend for the ability to read.
	 *
	 * @param targets the array of byte buffers into which the bytes will be copied.
	 * @param offset  the index of the first buffer to store bytes in.
	 * @param length  the maximum number of buffers to store bytes in.
	 * @return the number of bytes actually read.
	 * @throws AsynchronousCloseException if this channel is closed by another thread during this read
	 *                                    operation.
	 * @throws ClosedByInterruptException if another thread interrupts the calling thread while this
	 *                                    operation is in progress. The interrupt state of the calling
	 *                                    thread is set and the channel is closed.
	 * @throws ClosedChannelException     if this channel is closed.
	 * @throws IndexOutOfBoundsException  if {@code offset < 0} or {@code length < 0}, or if {@code
	 *                                    offset + length} is greater than the size of {@code targets}.
	 * @throws java.io.IOException        if another I/O error occurs.
	 * @throws NotYetConnectedException   if this channel is not yet connected.
	 * @see java.nio.channels.ScatteringByteChannel#read(java.nio.ByteBuffer[],
	 * int, int)
	 */
	@Override
	public long read(ByteBuffer[] targets, int offset, int length) throws IOException {
		return 0;
	}

	/**
	 * Writes bytes from the given byte buffer to this socket channel. The
	 * maximum number of bytes that are written is the remaining number of bytes
	 * in the buffer when this method is invoked. The bytes are taken from the
	 * buffer starting at the buffer's position.
	 * <p/>
	 * The call may block if other threads are also attempting to write to the
	 * same channel.
	 * <p/>
	 * Upon completion, the buffer's position is updated to the end of the bytes
	 * that have been written. The buffer's limit is not changed.
	 *
	 * @param source the byte buffer containing the bytes to be written.
	 * @return the number of bytes actually written.
	 * @throws AsynchronousCloseException if another thread closes the channel during the write.
	 * @throws ClosedByInterruptException if another thread interrupts the calling thread while this
	 *                                    operation is in progress. The interrupt state of the calling
	 *                                    thread is set and the channel is closed.
	 * @throws ClosedChannelException     if the channel was already closed.
	 * @throws java.io.IOException        if another I/O error occurs.
	 * @throws NotYetConnectedException   if this channel is not connected yet.
	 * @see java.nio.channels.WritableByteChannel#write(java.nio.ByteBuffer)
	 */
	@Override
	public int write(ByteBuffer source) throws IOException {
		return 0;
	}

	/**
	 * Attempts to write a subset of the given bytes from the buffers to this
	 * socket channel. This method attempts to write all {@code remaining()}
	 * bytes from {@code length} byte buffers, in order, starting at {@code
	 * sources[offset]}. The number of bytes actually written is returned.
	 * <p/>
	 * If a write operation is in progress, subsequent threads will block until
	 * the write is completed and then contend for the ability to write.
	 *
	 * @param sources the array of byte buffers that is the source for bytes written
	 *                to this channel.
	 * @param offset  the index of the first buffer in {@code buffers }to get bytes
	 *                from.
	 * @param length  the number of buffers to get bytes from.
	 * @return the number of bytes actually written to this channel.
	 * @throws AsynchronousCloseException if this channel is closed by another thread during this write
	 *                                    operation.
	 * @throws ClosedByInterruptException if another thread interrupts the calling thread while this
	 *                                    operation is in progress. The interrupt state of the calling
	 *                                    thread is set and the channel is closed.
	 * @throws ClosedChannelException     if this channel is closed.
	 * @throws IndexOutOfBoundsException  if {@code offset < 0} or {@code length < 0}, or if {@code
	 *                                    offset + length} is greater than the size of {@code sources}.
	 * @throws java.io.IOException        if another I/O error occurs.
	 * @throws NotYetConnectedException   if this channel is not yet connected.
	 * @see java.nio.channels.GatheringByteChannel#write(java.nio.ByteBuffer[],
	 * int, int)
	 */
	@Override
	public long write(ByteBuffer[] sources, int offset, int length) throws IOException {
		return 0;
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
