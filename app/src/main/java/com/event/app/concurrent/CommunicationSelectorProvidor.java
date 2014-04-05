package com.event.app.concurrent;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 * Created by goof_troop on 3/7/14.
 */
public class CommunicationSelectorProvidor extends SelectorProvider {

	/**
	 * Creates a new open {@code DatagramChannel}.
	 *
	 * @return the new channel.
	 * @throws java.io.IOException if an I/O error occurs.
	 */
	@Override
	public DatagramChannel openDatagramChannel() throws IOException {
		return null;
	}

	/**
	 * Creates a new {@code Pipe}.
	 *
	 * @return the new pipe.
	 * @throws java.io.IOException if an I/O error occurs.
	 */
	@Override
	public Pipe openPipe() throws IOException {
		return null;
	}

	/**
	 * Creates a new selector.
	 *
	 * @return the new selector.
	 * @throws java.io.IOException if an I/O error occurs.
	 */
	@Override
	public AbstractSelector openSelector() throws IOException {
		return null;
	}

	/**
	 * Creates a new open {@code ServerSocketChannel}.
	 *
	 * @return the new channel.
	 * @throws java.io.IOException if an I/O error occurs.
	 */
	@Override
	public ServerSocketChannel openServerSocketChannel() throws IOException {
		return null;
	}

	/**
	 * Create a new open {@code SocketChannel}.
	 *
	 * @return the new channel.
	 * @throws java.io.IOException if an I/O error occurs.
	 */
	@Override
	public SocketChannel openSocketChannel() throws IOException {
		return null;
	}
}
