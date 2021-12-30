package telran.application.dates;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ReminderAppl {

	private static final String DEFAULT_DURATION_FOR_APPLICATION = "3600";
	private static int intervalOfBeeps;
	private static ChronoUnit unitForIntervalOfBeeps;
	private static Instant instantStartTime;
	private static long durationOfApp;

	public static void main(String[] args) {
		// args[0] - mandatory interval of beeps
		// args[1] - mandatory ChronoUnit(according to time unit of ChronoUnit)
		// args[2] - optional in ChronoUnit values when to end up beeps (default) 1 hour
		// args[3] - optional in ChronoUnit values when to start beeping (default)
		// imidiatly
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
		if (args.length == 4) {
			while (Instant.now().isBefore(instantStartTime)) {
			}
		}
		startApp(intervalOfBeeps);
	}

	private static void setIntervalOfBeeps(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("You must write  interval of beeps");
		} else {
			try {
				intervalOfBeeps = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				throw new Exception("Interval must be a number");
			}
			if (intervalOfBeeps < 0) {
				throw new Exception("Interval can't be negative");
			}
		}
	}

	private static void setUnitForIntervalOfBeeps(String[] args) throws Exception {
		if (args.length < 2) {
			throw new Exception("You must write unit for interval of beeps");
		} else {
			try {
				unitForIntervalOfBeeps = ChronoUnit.valueOf(args[1].toUpperCase());
			} catch (IllegalArgumentException e) {
				for (ChronoUnit val : ChronoUnit.values()) {
					System.out.println(val);
				}
				throw new Exception("You must choose unit from list");
			}
		}
	}

	private static void setDurationForReminder(String[] args) throws Exception {
		if (args.length != 3) {
			durationOfApp = Long.parseLong(DEFAULT_DURATION_FOR_APPLICATION);
		} else {
			try {
				durationOfApp = Long.parseLong(args[2]);
			} catch (NumberFormatException e) {
				throw new Exception(String.format("Duration should be a number in %s", unitForIntervalOfBeeps));
			}
			if (durationOfApp < 0) {
				throw new Exception("Duration can't be negative");
			}
		}
	}

	private static void setStartTimeForApp(String[] args) throws Exception {
		long delayOnstart;
		if (args.length != 4) {
			instantStartTime = Instant.now();
		} else {
			try {
				delayOnstart = Integer.parseInt(args[3]);
				instantStartTime = Instant.now().plus(delayOnstart, unitForIntervalOfBeeps);
			} catch (NumberFormatException e) {
				throw new Exception(String.format("Start time should be a number in %s when need to start app",
						unitForIntervalOfBeeps));
			}
			if (delayOnstart < 0) {
				throw new Exception("Start time  can't be negative");
			}
		}
	}
	/**
	 * waiting for given time period in milliSeconds
	 * 
	 * @param periodInMillis
	 * @throws InterruptedException
	 */
	static void startApp( long periodInMillis) {
//	do while cycle with using instant objects and method chrono unit between
		Instant instantFinishTime = instantStartTime.plus(durationOfApp, unitForIntervalOfBeeps);
		do {
			System.out.println("\007");
			Instant periodInstant = Instant.now().plus(periodInMillis, unitForIntervalOfBeeps);
			while (Instant.now().isBefore(periodInstant)) {
			}
		} while (Instant.now().isBefore(instantFinishTime));
	}
}
