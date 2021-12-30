package telran.application.dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeWithZone {
	static ZonedDateTime timeZome = ZonedDateTime.now();

	public static void main(String[] args) {
		// args[0] - optional substring of time Zone (default local time zone)
		// display out the time in the appropriate ZoneId
		try {
			if (args.length > 0 && args[0].contains("-help")) {
				// V.R. The following line is the natural part of printHelp() method
				// It will be not bad idea to add something like
				// "Select one on following zones. Default value is Local Zone"
				System.out.printf("List of Zones: %s\n ", args[0]);
				printHelp();
				return;
			}
			timeZome = setZonedDateTime(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			printHelp();
			return;
		}
		// V.R. args[0] isn't existed in case "no params", and it will fail
		// It is the error!
		System.out.printf("Selected zone is :%s\n ", args[0]);
		printTimeZone(timeZome);

	}

	private static void printHelp() {
		for (String zone : ZoneId.getAvailableZoneIds()) {
			System.out.println(timeZome.withZoneSameInstant(ZoneId.of(zone)));
		}
	}

	private static void printTimeZone(ZonedDateTime timeZome) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss VV");
		System.out.printf("The time for selected Zone is: %s  \n", timeZome.format(format));
	}

	private static ZonedDateTime setZonedDateTime(String[] args) throws Exception {
		ZonedDateTime res = timeZome;
		if (args.length == 0) {
			return res;
		}
		/* V.R. This algorithm finds the first zone. It isn't always so.
		 * For example input string is "asia" and there are many suitable zones.
		 *  This case isn't supported at all
		 */
		for (String zone : ZoneId.getAvailableZoneIds()) {
			if (zone.toLowerCase().contains(args[0].toLowerCase())) {
				res = res.withZoneSameInstant(ZoneId.of(zone));
			}
		}
		if (res == timeZome) {
			throw new Exception("wrong  Zone name " + args[0]);
		}
		return res;
	}
}
