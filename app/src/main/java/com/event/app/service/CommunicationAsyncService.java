package com.event.app.service;

import android.os.AsyncTask;

import com.event.app.handler.CommunicationHandler;

/**
 * Boolean - return value. true is success; false if error or not success
 * Integer - progress measure
 *
 * Every Activity using CommunicationAsyncService MUST register for localBroadcasts to receive data.
 * Every Activity using CommunicationAsyncService MUST provide a string to register to for
 * localBroadcasts from Activity to send data.
 *
 */
public class CommunicationAsyncService extends AsyncTask<CommunicationHandler, Integer, Boolean> {

	private CommunicationHandler handler;

	// Sepecify what we need to braodcast to when we receive a result. The socket can return
	// complete data, but remain open, so multiple data blocks can be recevied. Specify a channel
	// to recieve data from. If no read channel is specified, then specify a data sink.
	// observer/observable pattern?

	/**
	 * Override this method to perform a computation on a background thread. The
	 * specified parameters are the parameters passed to {@link #execute}
	 * by the caller of this task.
	 * <p/>
	 * This method can call {@link #publishProgress} to publish updates
	 * on the UI thread.
	 *
	 * @param params The parameters of the task.
	 * @return A result, defined by the subclass of this task.
	 * @see #onPreExecute()
	 * @see #onPostExecute
	 * @see #publishProgress
	 */
	@Override
	protected Boolean doInBackground(CommunicationHandler... params) {

		if (params.length != 1 || params[0] == null)
			throw new IllegalArgumentException("varargs params");

		this.handler = params[0];

		return true;
	}

	/**
	 * <p>Runs on the UI thread after {@link #doInBackground}. The
	 * specified result is the value returned by {@link #doInBackground}.</p>
	 * <p/>
	 * <p>This method won't be invoked if the task was cancelled.</p>
	 *
	 * @param o The result of the operation computed by {@link #doInBackground}.
	 * @see #onPreExecute
	 * @see #doInBackground
	 * @see #onCancelled(Object)
	 */
	@Override
	protected void onPostExecute(Boolean o) {
		super.onPostExecute(o);

		this.handler.clean();
	}

	/**
	 * <p>Runs on the UI thread after {@link #cancel(boolean)} is invoked and
	 * {@link #doInBackground(Object[])} has finished.</p>
	 * <p/>
	 * <p>The default implementation simply invokes {@link #onCancelled()} and
	 * ignores the result. If you write your own implementation, do not call
	 * <code>super.onCancelled(result)</code>.</p>
	 *
	 * @param o The result, if any, computed in
	 *          {@link #doInBackground(Object[])}, can be null
	 * @see #cancel(boolean)
	 * @see #isCancelled()
	 */
	@Override
	protected void onCancelled(Boolean o) {
		super.onCancelled(o);
	}

}
