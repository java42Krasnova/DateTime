package telran.application.dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeWithZone {
	static ZonedDateTime timeZome = ZonedDateTime.now();;

	public static void main(String[] args) {
		// args[0] - optional substring of time Zone (default local time zone)
		// display out the time in the appropriate ZoneId
		try {
			if (args.length > 0 && args[0].contains("-help")) {
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
			return ZonedDateTime.now();
		}
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
