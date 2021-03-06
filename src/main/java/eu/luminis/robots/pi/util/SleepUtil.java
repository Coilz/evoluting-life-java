package eu.luminis.robots.pi.util;

public class SleepUtil {

	public static void sleep(long millis) {
		try {
		    Thread.sleep(millis);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
	}

	public static void sleep(long millis, int nanos) {
		try {
		    Thread.sleep(millis, nanos);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
	}
	
}
