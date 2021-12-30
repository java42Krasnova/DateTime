package telran.application.dates;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ReminderAppl {

	private static int intervalOfBeeps;
	private static ChronoUnit unitForIntervalOfBeeps;
	private static Instant instantStartTime = Instant.now();
	private static long durationOfApp = 3600;

	public static void main(String[] args) {
		// args[0] - mandatory interval of beeps
		// args[1] - mandatory ChronoUnit(according to time unit of ChronoUnit)
		// args[2] - optional in ChronoUnit values when to end up beeps (default) 1 hour
		// args[3] - optional in ChronoUnit values when to start beeping (default) immediately
		// beep - syso("\007")
		try {
			if (args.length > 0 && args[0].contains("-help")) {
				System.out.println("Please write the period for beeps,"
						+ " units for period, duration for app and time in sec when the app will starts");
				return;
			}
			setIntervalOfBeeps(args);
			setUnitForIntervalOfBeeps(args);
			setDurationForReminder(args);
			setStartTimeForApp(args);

		} catch (RuntimeException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		startApp(intervalOfBeeps);
	}

	private static void setIntervalOfBeeps(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("You must write interval of beeps");
		}
		try {
			intervalOfBeeps = Integer.parseInt(args[0]);
			if (intervalOfBeeps < 0) {
				throw new Exception("Interval can't be negative");
			}
		} catch (NumberFormatException e) {
			throw new Exception("Interval must be a number");
		}
	}

	private static void setUnitForIntervalOfBeeps(String[] args) throws Exception {
		if (args.length < 2) {
			throw new Exception("You must write unit for interval of beeps");
		}
		try {
			unitForIntervalOfBeeps = ChronoUnit.valueOf(args[1].toUpperCase());
		} catch (Exception e) {
			throw new Exception("Wrong units " + args[1]);
		}
	}

	private static void setDurationForReminder(String[] args) throws Exception {
		if (args.length > 2) {
			try {
				durationOfApp = Long.parseLong(args[2]);
				if (durationOfApp < 0) {
					throw new Exception("Duration can't be negative");
				}
			} catch (NumberFormatException e) {
				throw new Exception(String.format("Duration should be a number in %s", unitForIntervalOfBeeps));
			}
		}
	}

	private static void setStartTimeForApp(String[] args) throws Exception {
		long delayOnstart;
		if (args.length >= 4) {
			try {
				delayOnstart = Integer.parseInt(args[3]);
				if (delayOnstart < 0) {
					throw new Exception("Start time  can't be negative");
				}
				instantStartTime = Instant.now().plus(delayOnstart, unitForIntervalOfBeeps);
			} catch (NumberFormatException e) {
				throw new Exception(String.format("Start time should be a number in %s when need to start app",
						unitForIntervalOfBeeps));
			}
		}
	}

	/**
	 * waiting for given time period in milliSeconds
	 * 
	 * @param periodInMillis
	 * @throws InterruptedException
	 */
	static void startApp(long periodInMillis) {
//	do while cycle with using instant objects and method chrono unit between
		if(instantStartTime != Instant.now()) {
		waitFor(instantStartTime);
		}
		Instant instantFinishTime = instantStartTime.plus(durationOfApp, unitForIntervalOfBeeps);
		do {
			System.out.println("007");
			Instant periodInstant = Instant.now().plus(periodInMillis, unitForIntervalOfBeeps);

			waitFor(periodInstant);
		} while (Instant.now().isBefore(instantFinishTime));
	}

	private static void waitFor(Instant periodInstant) {
		while (Instant.now().isBefore(periodInstant)) {
		}
	}
}
