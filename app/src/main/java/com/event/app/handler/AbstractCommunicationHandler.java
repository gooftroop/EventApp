package com.event.app.handler;

/**
 * Created by goof_troop on 2/18/14.
 */
public abstract class AbstractCommunicationHandler implements CommunicationHandler {

	public AbstractCommunicationHandler() {
		// TODO configure data publishing and receiving
	}

	public void saveState() {
		// TODO save the state the communication is in
	}

	// TODO resume state

	public abstract void clean();

}
