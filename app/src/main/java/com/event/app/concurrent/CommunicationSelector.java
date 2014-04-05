package com.event.app.concurrent;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.ClosedSelectorException;
import java.util.Set;

/**
 *
 */
public class CommunicationSelector extends AbstractSelector {

	/**
	 *
	 */
	public CommunicationSelector() {

		super(new CommunicationSelectorProvidor());
	}

	/**
	 * Implements the closing of this channel.
	 */
	@Override
	protected void implCloseSelector() throws IOException {

	}

	/**
	 * Registers {@code channel} with this selector.
	 *
	 * @param channel
	 * @param operations the {@link java.nio.channels.SelectionKey interest set} of {@code
	 *                   channel}.
	 * @param attachment the attachment for the selection key.
	 * @return the key related to the channel and this selector.
	 */
	@Override
	protected SelectionKey register(AbstractSelectableChannel channel, int operations, Object attachment) {
		return null;
	}

	/**
	 * Gets the set of registered keys. The set is immutable and is not thread-
	 * safe.
	 *
	 * @return the set of registered keys.
	 */
	@Override
	public Set<SelectionKey> keys() {
		return null;
	}

	/**
	 * Detects if any of the registered channels is ready for I/O operations
	 * according to its {@link java.nio.channels.SelectionKey interest set}. This method does not
	 * return until at least one channel is ready, {@link #wakeup()} is
	 * invoked or the calling thread is interrupted.
	 *
	 * @return the number of channels that are ready for operation.
	 * @throws java.io.IOException     if an I/O error occurs.
	 * @throws ClosedSelectorException if the selector is closed.
	 */
	@Override
	public int select() throws IOException {
		return 0;
	}

	/**
	 * Detects if any of the registered channels is ready for I/O operations
	 * according to its {@link java.nio.channels.SelectionKey interest set}. This method does not
	 * return until at least one channel is ready, {@link #wakeup()} is invoked,
	 * the calling thread is interrupted or the specified {@code timeout}
	 * expires.
	 *
	 * @param timeout the non-negative timeout in millisecond; 0 will block forever
	 *                if no channels get ready.
	 * @return the number of channels that are ready for operation.
	 * @throws ClosedSelectorException  if the selector is closed.
	 * @throws IllegalArgumentException if the given timeout argument is less than zero.
	 * @throws java.io.IOException      if an I/O error occurs.
	 */
	@Override
	public int select(long timeout) throws IOException {
		return 0;
	}

	/**
	 * Gets the selection keys whose channels are ready for operation. The set
	 * is not thread-safe and no keys may be added to it. Removing keys is
	 * allowed.
	 *
	 * @return the selection keys whose channels are ready for operation.
	 * @throws ClosedSelectorException if the selector is closed.
	 */
	@Override
	public Set<SelectionKey> selectedKeys() {
		return null;
	}

	/**
	 * Detects if any of the registered channels is ready for I/O operations
	 * according to its {@link java.nio.channels.SelectionKey interest set}. This operation will
	 * return immediately.
	 *
	 * @return the number of channels that are ready for operation, 0 if none is
	 * ready.
	 * @throws java.io.IOException     if an I/O error occurrs.
	 * @throws ClosedSelectorException if the selector is closed.
	 */
	@Override
	public int selectNow() throws IOException {
		return 0;
	}

	/**
	 * Forces blocked {@code select} operations to return immediately.
	 * <p/>
	 * If no {@code select} operation is blocked when {@code wakeup()} is called
	 * then the next {@code select} operation will return immediately. This can
	 * be undone by a call to {@code selectNow()}; after calling
	 * {@code selectNow()}, a subsequent call of {@code select} can block
	 * again.
	 *
	 * @return this selector.
	 * @throws ClosedSelectorException if the selector is closed.
	 */
	@Override
	public Selector wakeup() {
		return null;
	}
}
