package com.event.app.network;

import com.event.app.database.RemoteSQLCursor;

/**
 * Created by goof_troop on 3/19/14.
 */
public interface HttpRequestCallback {

	/**
	 *
	 */
	public void callbackResult(RemoteSQLCursor cursor);

	/**
	 *
	 */
	public void callbackError(Exception e);
}
