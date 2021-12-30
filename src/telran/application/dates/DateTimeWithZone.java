package telran.application.dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DateTimeWithZone {
	static ZonedDateTime timeZone = ZonedDateTime.now();
	static ArrayList<ZonedDateTime> listOftimeZones = new ArrayList<>();

	public static void main(String[] args) {
		// args[0] - optional substring of time Zone (default local time zone)
		// display out the time in the appropriate ZoneId
		try {
			if (args.length > 0 && args[0].contains("-help")) {
				System.out.printf("List of Zones: %s\n ", args[0]);
				printHelp();
				return;
			}
			if (args.length > 0) {
				System.out.printf("Selected zone is :%s\n ", args[0]);
			}
			listOftimeZones = getZonedDateTime(args);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			printHelp();
			return;
		}
		printTimeZone(listOftimeZones);

	}

	private static void printHelp() {
		System.out.println("Select one of following Zones. Default Zone is Local Zone");
		for (String zone : ZoneId.getAvailableZoneIds()) {
			System.out.println(timeZone.withZoneSameInstant(ZoneId.of(zone)));
		}
	}

	private static void printTimeZone(ArrayList<ZonedDateTime> listTimeZome) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss VV");
		for (ZonedDateTime zdt : listTimeZome) {
			System.out.printf("The time for selected Zone is: %s  \n", zdt.format(format));
		}
	}

	private static ArrayList<ZonedDateTime> getZonedDateTime(String[] args) throws Exception {
		ArrayList<ZonedDateTime> res = new ArrayList<>();
		if (args.length == 0) {
			res.add(timeZone);
			return res;
		}
		for (String zone : ZoneId.getAvailableZoneIds()) {
			if (zone.toLowerCase().contains(args[0].toLowerCase())) {
				res.add(timeZone.withZoneSameInstant(ZoneId.of(zone)));
			}
		}
		if (res.isEmpty()) {
			throw new Exception("Wrong  Zone name " + args[0]);
		}
		return res;
	}
}
