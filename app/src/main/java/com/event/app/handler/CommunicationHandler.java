package com.event.app.handler;

/**
 * User -> decides to do a communication action -> Activity -> onClick for service:
 *
 * 1. Get an accept on the sockets
 * 2. Verify trust
 * 3.
 *
 *  - If receiving data - register the receiver to receive a broadcast
 *  - If sending data but not receiving data - setup communication with data source,
 *    but no registering
 *  - If sending data and receiving data - setup communication with data source and register the
 *    receiver to receive a broadcast
 *  - If user input is required while transfer is occurring, register a write channel to the
 *    handler
 *
 * 4. The handler top-level setups the communication object at instantion. In doInBackground, call
 *    the socket threads using intents
 *
 *
 * The communication must be async; it cannot block the UI thread. Therefore we use AsyncTask. The
 * AsyncTask provides us doInBackground, which will run the communication. Contrary to the API, we
 * will not expect anything back, so there's no need for a return value.
 *
 * Receiver
 * From this level, we don't know what's receiving the data, so the best we can do is fire an event
 * when data is received. Same with the communication handler receiving data.
 */
public interface CommunicationHandler {

	public void clean();

	// NOTE create recevier in handlers
}
