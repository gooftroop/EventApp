package com.event.app.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by goof_troop on 2/13/14.
 */
public class ConnectionManager extends ThreadPoolExecutor {

	public ConnectionManager() {

		super(0, Integer.MAX_VALUE, Long.MAX_VALUE, TimeUnit.SECONDS,
		      new ArrayBlockingQueue<Runnable>(100, true));
	}


	/**
	 * Method invoked upon completion of execution of the given Runnable.
	 * This method is invoked by the thread that executed the task. If
	 * non-null, the Throwable is the uncaught {@code RuntimeException}
	 * or {@code Error} that caused execution to terminate abruptly.
	 * <p/>
	 * <p>This implementation does nothing, but may be customized in
	 * subclasses. Note: To properly nest multiple overridings, subclasses
	 * should generally invoke {@code super.afterExecute} at the
	 * beginning of this method.
	 * <p/>
	 * <p><b>Note:</b> When actions are enclosed in tasks (such as
	 * {@link java.util.concurrent.FutureTask}) either explicitly or via methods such as
	 * {@code submit}, these task objects catch and maintain
	 * computational exceptions, and so they do not cause abrupt
	 * termination, and the internal exceptions are <em>not</em>
	 * passed to this method. If you would like to trap both kinds of
	 * failures in this method, you can further probe for such cases,
	 * as in this sample subclass that prints either the direct cause
	 * or the underlying exception if a task has been aborted:
	 * <p/>
	 * <pre> {@code
	 * class ExtendedExecutor extends ThreadPoolExecutor {
	 *   // ...
	 *   protected void afterExecute(Runnable r, Throwable t) {
	 *     super.afterExecute(r, t);
	 *     if (t == null && r instanceof Future<?>) {
	 *       try {
	 *         Object result = ((Future<?>) r).get();
	 *       } catch (CancellationException ce) {
	 *           t = ce;
	 *       } catch (ExecutionException ee) {
	 *           t = ee.getCause();
	 *       } catch (InterruptedException ie) {
	 *           Thread.currentThread().interrupt(); // ignore/reset
	 *       }
	 *     }
	 *     if (t != null)
	 *       System.out.println(t);
	 *   }
	 * }}</pre>
	 *
	 * @param r the runnable that has completed
	 * @param t the exception that caused termination, or null if
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
	}
}
